TARGET?=libhash

build_hash:
	@echo "-- create object files"
	gcc -c *.c
	@echo "-- create archive with bundle files"
	ar rs $(TARGET).a *.o

clean:
	@echo "-- cleaning *.o and *.a files"
	rm -rf *.o *.a
