MODNAME := remember
obj-m := $(MODNAME).o
KDIR := /lib/modules/$(shell uname -r)/build
PWD := $(shell pwd)
default:
	$(MAKE) -C $(KDIR) SUBDIRS=$(PWD) modules

clean:
	/bin/rm -f *.o *.ko *~
	/bin/rm -f Module.symvers modules.order Modules.symvers *.mod.c
	/bin/rm -f .*o.cmd .cache.mk
	/bin/rm -rf .tmp_versions

zip:
	rm -f $(MODNAME).zip
	mkdir $(MODNAME)
	cp -a Makefile $(MODNAME).c $(MODNAME)
	zip -r $(MODNAME).zip $(MODNAME)
	rm -rf $(MODNAME)
