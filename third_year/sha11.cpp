
//	******************************************************************
//	*  sha11.cpp : program pentru implementarea functiei hash sha-1  *
//	******************************************************************

#include "stdafx.h"
#include "sha11.h"
#include <string.h>
#include <stdio.h>

//	==================================================================
//	subrutine
//	------------------------------------------------------------------

void initHs()
{
	// load the initial values for H's
    H[0] = 0x67452301;
    H[1] = 0xEFCDAB89;
    H[2] = 0x98BADCFE;
    H[3] = 0x10325476;
    H[4] = 0xC3D2E1F0;
}

//	------------------------------------------------------------------

void initKs()
{
	// load the initial values for K's
    int i = 0;
    for (i; i < 20; i++) {
        K[i] = 0x5A827999;
    }
    for (i; i < 40; i++) {
        K[i] = 0x6ED9EBA1;
    }
    for (i; i < 60; i++) {
        K[i] = 0x8F1BBCDC;
    }
    for (i; i < 80; i++) {
        K[i] = 0xCA62C1D6;
    }
}

//	------------------------------------------------------------------

int getMessage(int k)
{
	// copy message k from msg[k] to binmsg
    int msg_len = strlen(msg[k]);
    memcpy(binmsg, msg[k], msg_len);

    return msg_len;
}

//	------------------------------------------------------------------

int paddMessage(int meslen)
{
	// pad the binmsg array according to the specification
	// return the number of blocks of the padded message
    int numZeros, numBlocks;
    numZeros = (55 - meslen) % 64;          // (64 - (meslen % 64 + 9)) % 64;
    numBlocks = (meslen + 8) / 64 + 1;      // ceil((meslen + 9) / 64));
    // do the padding
    memset(binmsg + meslen, 0x80, 1);
    memset(binmsg + meslen + 1, 0, numZeros + 4);
    PUT_UINT32(meslen * 8, binmsg, numBlocks * 64 - 4);

    return numBlocks;
}

//	------------------------------------------------------------------

void getWsfromM(int curblock)
{
	// fill out the W[] from the current block
    int i = 0;
    for (i; i < 16; i++) {
        GET_UINT32(W[i], binmsg, 64 * curblock + i * 4);
    }
}

//	------------------------------------------------------------------

void getAsfromHs()
{
	// initialize A, B, C, D, E from H's
    A = H[0]; B = H[1]; C = H[2]; D = H[3]; E = H[4];
}

//	------------------------------------------------------------------

void displayDigest(uint32 H[])
{
	printf(" digest value - %9x %9x %9x %9x %9x\n\n", 
		H[0], H[1], H[2], H[3], H[4]);
}

//	==================================================================

int main(int argc, char* argv[])
{
	int i, j, k;
	int meslen;
	int numblocks;

	// parse all 3 test messages

	for (k=0; k<3; k++)
	{

		initKs();
		initHs();

		for (i=0; i<4096; i++)
		{
			memset((void *)(binmsg + i), 0, 1);
		}	

		meslen = getMessage(k);
		numblocks = paddMessage(meslen);	

		for (i=0; i<numblocks; i++)
		{
			getWsfromM(i);
		
			for (j=16; j<80; j++)
			{
				W[j] = W[j-3]^W[j-8]^W[j-14]^W[j-16];
				W[j] = S(W[j], 1);
			}	

			getAsfromHs();

			for (j=0; j<80; j++)
			{
				TEMP = S(A,5);
				int cit = j / 20;

				switch (cit)
				{
				case 0: 
					TEMP += F0(B,C,D);
					break;
				case 1:
					TEMP += F1(B,C,D);
					break;
				case 2:
					TEMP += F2(B,C,D);
					break;
				case 3:
					TEMP += F3(B,C,D);
					break;
				}

				TEMP = (TEMP + E + W[j] + K[j]) & 0xFFFFFFFF;

				E=D;
				D=C;
				C=S(B,30);
				B=A;
				A=TEMP;
//				printf("%9x %9x %9x %9x %9x\n", A, B, C, D, E);
			}

			H[0] = H[0] + A;
			H[1] = H[1] + B;
			H[2] = H[2] + C;
			H[3] = H[3] + D;
			H[4] = H[4] + E;
		}

		displayDigest(H);
	}

	return 0;
}

//	******************************************************************