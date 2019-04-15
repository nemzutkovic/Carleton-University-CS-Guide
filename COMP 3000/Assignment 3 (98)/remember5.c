/* 
  remember.c

  remember module COMP 3000, Carleton University
  remembers what is written to it, returns in when read along with
  info about how it is stored

  License: GPLv2 or later
  Author: Anil Somayaji
  November 3, 2018

  device driver and module code derived from:
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
#include <linux/uaccess.h>
#include <linux/mm.h>

#define DEVICE_NAME "remember"
#define CLASS_NAME "misc"

static struct class* remember_class = NULL;
static struct device* remember_device = NULL;
static int remember_major;

static struct page *saved_data_page = NULL;
static char *saved_data = NULL;
static unsigned long saved_data_len = 0;
static int saved_data_max = PAGE_SIZE;
static int saved_data_order = 0;

static int remember_open(struct inode *the_inode, struct file *f)
{
        pr_info("Remember: device opened\n");
        return 0;
}

static ssize_t remember_read(struct file *f, char *buf, size_t len, loff_t *offset)
{
        unsigned long n;
        char *error_msg = "Buffer too small.";
        
        pr_info("Remember: read started\n");
        
        if (*offset > 0) {
                pr_info("Remember: read non-zero offset, aborting\n");
                return 0;
        }

        if (len < saved_data_len) {
                pr_info("Remember: read short buffer\n");
                n = strlen(error_msg) + 1;  // Include terminating null byte
                if (n > len) {
                        n = len;
                }
                copy_to_user(buf, error_msg, n);
                
                return n;
        } else {
                pr_info("Remember: read returning data, %ld bytes\n",
                        saved_data_len);
                copy_to_user(buf, saved_data, saved_data_len);
                *offset = saved_data_len;

                return saved_data_len;
        }
}

void init_saved_data(void)
{
        pr_info("Remember: allocating data page");

        if (saved_data) {
                pr_err("Remember: saved_data already initialized!");
        } else {       
                saved_data_page = alloc_pages(GFP_KERNEL,
                                              saved_data_order);
                saved_data = (char *) page_address(saved_data_page);
                saved_data_len = 0;

                pr_info("saved_data at kernel virtual address %lx",
                        (unsigned long) saved_data);
                pr_info("saved_data_page page struct at address %lx",
                        (unsigned long) saved_data_page);
        }
}

void free_saved_data(void)
{
        if (saved_data_page) {
                pr_info("Remember: freeing old data page");
                __free_pages(saved_data_page, saved_data_order);
                saved_data_page = NULL;
                saved_data = NULL;
                saved_data_len = 0;
        }
}

static ssize_t remember_write(struct file *f, const char *buf, size_t len,
                           loff_t *offset)
{
        unsigned long result;

        if (*offset > 0) {
                pr_info("Remember: write nonzero offset, aborting");

                return 0;
        }
        
        free_saved_data();
        init_saved_data();
        
        if (len > saved_data_max) {
                len = saved_data_max;
        }
        
        pr_info("Remember: write saving data, %ld bytes", len);

        result = copy_from_user(saved_data, buf, len);
        saved_data_len = len;        

        *offset = len;
        
        return len;
}

static int remember_release(struct inode *the_inode, struct file *f)
{        
        pr_info("Remember: device closed\n");
        return 0;
}


static struct file_operations remember_fops = {
        .open = remember_open,
        .read = remember_read,
        .write = remember_write,
        .release = remember_release,
};


static char *remember_devnode(struct device *dev, umode_t *mode)
{
        if (mode)
                *mode = 0666;
        return NULL;
}

static int __init remember_init(void)
{
        int retval;
  
        remember_major = register_chrdev(0, DEVICE_NAME, &remember_fops);
        if (remember_major < 0) {
                pr_err("failed to register device: error %d\n", remember_major);
                retval = remember_major;
                goto failed_chrdevreg;
        }
 
        remember_class = class_create(THIS_MODULE, CLASS_NAME);
        if (IS_ERR(remember_class)) {
                pr_err("Remember: failed to register device class '%s'\n",
                       CLASS_NAME);
                retval = PTR_ERR(remember_class);
                goto failed_classreg;
        }
 
        remember_class->devnode = remember_devnode;

        remember_device = device_create(remember_class, NULL,
                                        MKDEV(remember_major, 0),
                                        NULL, DEVICE_NAME);

        if (IS_ERR(remember_device)) {
                pr_err("Remember: failed to create device '%s'\n", DEVICE_NAME);
                retval = PTR_ERR(remember_device);
                goto failed_devreg;
        }
        
        pr_info("Remember: device registered using major %d.\n",
                remember_major);
        
        return 0;
        
 failed_devreg:
        class_unregister(remember_class);
        class_destroy(remember_class);
 failed_classreg:
        unregister_chrdev(remember_major, DEVICE_NAME);
 failed_chrdevreg:
        return -1;
}

static void __exit remember_exit(void)
{
        free_saved_data();

        device_destroy(remember_class, MKDEV(remember_major, 0));
        class_unregister(remember_class);
        class_destroy(remember_class);
        unregister_chrdev(remember_major, "remember");
        pr_info("Unloading Remember module.\n");
        return;
}

module_init(remember_init);
module_exit(remember_exit);

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Anil Somayaji <soma@scs.carleton.ca>");
MODULE_DESCRIPTION("A remember character device module");
