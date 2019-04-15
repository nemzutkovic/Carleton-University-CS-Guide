#include <linux/module.h>
#include <linux/init.h>
#include <linux/kernel.h>

static int __init simple_init(void)
{
        printk ("Hello kernel world!\n");
        return 0;
}

static void __exit simple_exit(void)
{
        printk ("Goodbye kernel world.\n");
        return;
}

module_init(simple_init);
module_exit(simple_exit);

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Anil Somayaji <soma@scs.carleton.ca>");
MODULE_DESCRIPTION("A simple module");
