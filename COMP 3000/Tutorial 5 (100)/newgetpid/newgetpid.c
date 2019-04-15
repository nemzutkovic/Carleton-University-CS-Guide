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
#include <linux/sched.h>
#include <asm/uaccess.h>

#define dbg(format, arg...) do { if (debug) pr_info(CLASS_NAME ": %s: " format, __FUNCTION__, ## arg); } while (0)
#define err(format, arg...) pr_err(CLASS_NAME ": " format, ## arg)
#define info(format, arg...) pr_info(CLASS_NAME ": " format, ## arg)
#define warn(format, arg...) pr_warn(CLASS_NAME ": " format, ## arg)


#define DEVICE_NAME "describe" // modified from newgetpid
#define CLASS_NAME "comp3000"

static struct class* newgetpid_class = NULL;
static struct device* newgetpid_device = NULL;
static int newgetpid_major;

static int newgetpid_open(struct inode *the_inode, struct file *f)
{
        return 0;
}

static ssize_t newgetpid_read(struct file *f, char *buf, size_t len, loff_t *offset)
{
        size_t i, msglen;
        pid_t thepid;
	pid_t theppid;
	const struct cred *cred = current_cred();

        char message[1000];

        if (*offset > i) {		
                return 0;
        }
        
        thepid  = task_tgid_vnr(current);
	theppid = task_ppid_nr(current);
	snprintf(message, 1000, "Your PID is %d.\nYour PPID is %d.\nYour UID is %d.\nYour GID is %d.\nYour EUID is %d.\nYour EGID is %d.\n", thepid, theppid, cred->uid, cred->gid, cred->euid, cred->egid);
	
        msglen = strlen(message);

        if (len < msglen) {
                msglen = len;
        }

        for (i = 0; i < msglen; i++) {
                put_user(message[i], buf++);
        }

        *offset = i;

        return i;
}

static int newgetpid_release(struct inode *the_inode, struct file *f)
{
        printk(KERN_ALERT "Newgetpid device closed\n");
        return 0;
}


static struct file_operations newgetpid_fops = {
        .open = newgetpid_open,
        .read = newgetpid_read,
        .release = newgetpid_release,
};


static char *newgetpid_devnode(struct device *dev, umode_t *mode)
{
        if (mode)
	        *mode = 0444;
        return NULL;
}

static int __init newgetpid_init(void)
{
        int retval;
  
        newgetpid_major = register_chrdev(0, DEVICE_NAME, &newgetpid_fops);
        if (newgetpid_major < 0) {
                err("failed to register device: error %d\n", newgetpid_major);
                retval = newgetpid_major;
                goto failed_chrdevreg;
        }
 
        newgetpid_class = class_create(THIS_MODULE, CLASS_NAME);
        if (IS_ERR(newgetpid_class)) {
                err("failed to register device class '%s'\n", CLASS_NAME);
                retval = PTR_ERR(newgetpid_class);
                goto failed_classreg;
        }
 
	newgetpid_class->devnode = newgetpid_devnode;

        newgetpid_device = device_create(newgetpid_class, NULL, MKDEV(newgetpid_major, 0),
                                    NULL, DEVICE_NAME);

        if (IS_ERR(newgetpid_device)) {
                err("failed to create device '%s'\n", DEVICE_NAME);
                retval = PTR_ERR(newgetpid_device);
                goto failed_devreg;
        }
        
        info("Newgetpid device registered using major %d.\n", newgetpid_major);
        
        return 0;
        
 failed_devreg:
        class_unregister(newgetpid_class);
        class_destroy(newgetpid_class);
 failed_classreg:
        unregister_chrdev(newgetpid_major, DEVICE_NAME);
 failed_chrdevreg:
        return -1;
}

static void __exit newgetpid_exit(void)
{
        device_destroy(newgetpid_class, MKDEV(newgetpid_major, 0));
        class_unregister(newgetpid_class);
        class_destroy(newgetpid_class);
        unregister_chrdev(newgetpid_major, "newgetpid");
        info("Unloading Newgetpid module.\n");
        return;
}

module_init(newgetpid_init);
module_exit(newgetpid_exit);

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Anil Somayaji <soma@scs.carleton.ca>");
MODULE_DESCRIPTION("A write newgetpid character device module");
