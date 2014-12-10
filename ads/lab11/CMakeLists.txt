cmake_minimum_required(VERSION 2.8.4)
project(lab11)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES Main.cpp)
add_executable(lab11 ${SOURCE_FILES})