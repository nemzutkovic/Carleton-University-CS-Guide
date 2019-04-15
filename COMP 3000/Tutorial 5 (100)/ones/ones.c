/* Code derived from:
  https://appusajeev.wordpress.com/2011/06/18/writing-a-linux-character-device-driver/
  and
  http://pete.akeo.ie/2011/08/writing-linux-device-driver-for-kernels.html
*/

#include <linux/module.h>
#include <linux/string.h>
#include <linux/fs.h>
#include <linux/device.h>
#include <linux/init.h>
#include <linux/kernel.h>
#include <asm/uaccess.h>

#define dbg(format, arg...) do { if (debug) pr_info(CLASS_NAME ": %s: " format, __FUNCTION__, ## arg); } while (0)
#define err(format, arg...) pr_err(CLASS_NAME ": " format, ## arg)
#define info(format, arg...) pr_info(CLASS_NAME ": " format, ## arg)
#define warn(format, arg...) pr_warn(CLASS_NAME ": " format, ## arg)


#define DEVICE_NAME "ones"
#define CLASS_NAME "comp3000"

static struct class* ones_class = NULL;
static struct device* ones_device = NULL;
static int ones_major;

static int ones_open(struct inode *the_inode, struct file *f)
{
        return 0;
}

static ssize_t ones_read(struct file *f, char *buf, size_t len, loff_t *offset)
{
        size_t i;

        for (i = 0; i < len; i++) {
                put_user('1', buf++);
        }

        return i;
}

static int ones_release(struct inode *the_inode, struct file *f)
{
        printk(KERN_ALERT "Ones device closed\n");
        return 0;
}


static struct file_operations ones_fops = {
        .open = ones_open,
        .read = ones_read,
        .release = ones_release,
};


static char *ones_devnode(struct device *dev, umode_t *mode)
{
        if (mode)
	        *mode = 0444;
        return NULL;
}

static int __init ones_init(void)
{
        int retval;
  
        ones_major = register_chrdev(0, DEVICE_NAME, &ones_fops);
        if (ones_major < 0) {
                err("failed to register device: error %d\n", ones_major);
                retval = ones_major;
                goto failed_chrdevreg;
        }
 
        ones_class = class_create(THIS_MODULE, CLASS_NAME);
        if (IS_ERR(ones_class)) {
                err("failed to register device class '%s'\n", CLASS_NAME);
                retval = PTR_ERR(ones_class);
                goto failed_classreg;
        }
 
	ones_class->devnode = ones_devnode;

        ones_device = device_create(ones_class, NULL, MKDEV(ones_major, 0),
                                    NULL, DEVICE_NAME);

        if (IS_ERR(ones_device)) {
                err("failed to create device '%s'\n", DEVICE_NAME);
                retval = PTR_ERR(ones_device);
                goto failed_devreg;
        }
        
	info("Oops!\n");
        info("Ones device registered using major %d.\n", ones_major);
        
        return 0;
        
 failed_devreg:
        class_unregister(ones_class);
        class_destroy(ones_class);
 failed_classreg:
        unregister_chrdev(ones_major, DEVICE_NAME);
 failed_chrdevreg:
        return -1;
}

static void __exit ones_exit(void)
{
        device_destroy(ones_class, MKDEV(ones_major, 0));
        class_unregister(ones_class);
        class_destroy(ones_class);
        unregister_chrdev(ones_major, "ones");
        info("Unloading Ones module.\n");
        return;
}

module_init(ones_init);
module_exit(ones_exit);

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Anil Somayaji <soma@scs.carleton.ca>");
MODULE_DESCRIPTION("A write ones character device module");
