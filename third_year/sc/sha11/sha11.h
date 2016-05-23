
//	*****************************************************************
//	*  sha11.h : macro-uri si declaratii pentru functia hash sha-1  *
//	*****************************************************************

#ifndef _SHA11_H
#define _SHA11_H

#define uint8 unsigned char
#define uint32 unsigned long

#define S(x,n) ((x<<n)|((x&0XFFFFFFFF)>>(32-n)))

#define F0(B,C,D) (D^(B&(C^D)))
#define F1(B,C,D) (B^C^D)
#define F2(B,C,D) (B&C)|(B&D)|(C&D)
#define F3(B,C,D) (B^C^D)

#define GET_UINT32(n,b,i)                       \
{                                               \
    (n) = ( (uint32) (b)[(i)    ] << 24 )       \
        | ( (uint32) (b)[(i) + 1] << 16 )       \
        | ( (uint32) (b)[(i) + 2] <<  8 )       \
        | ( (uint32) (b)[(i) + 3]       );      \
}

#define PUT_UINT32(n,b,i)                       \
{                                               \
    (b)[(i)    ] = (uint8) ( (n) >> 24 );       \
    (b)[(i) + 1] = (uint8) ( (n) >> 16 );       \
    (b)[(i) + 2] = (uint8) ( (n) >>  8 );       \
    (b)[(i) + 3] = (uint8) ( (n)       );       \
}

uint32 K[80];
uint32 H[5];
uint32 W[80];
uint32 A, B, C, D, E;
uint32 TEMP;
uint8 binmsg[4096];

//	date de intrare pentru teste

char * msg[] = 
{
	"",
	"abc",
	"abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq"
};

#endif	// _SHA11_H

//	******************************************************************


