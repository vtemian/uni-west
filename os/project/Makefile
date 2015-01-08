OUTPUT?=ash
TARGET?=ash.c

build: deps
	@echo "-- static compiling ash"
	gcc --static -Wall $(TARGET) -Lhash/ -Ihash/ -o $(OUTPUT) -lhash

deps:
	@echo "-- compiling hash"
	make -C hash -s

clean:
	rm -rf $(OUTPUT)
