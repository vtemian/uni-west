
//	************************************************************
//	*  aes11.cpp : program de implementare a algoritmului aes  *
//	************************************************************

#include "StdAfx.h"
#include "aes11.h"
#include "string.h"
#include <stdio.h>

//	====================================================================

//	subrutine

//	--------------------------------------------------------------------

uint32 SubWord(uint32 T)
{
	// returneaza un unsigned log (uint32) obtinut in urma aplicarii
	// sandbox-ului la uint32 - ul de intrare T
	uint8 temp[4];
	PUT_UINT32(T, temp, 0);
	for( int i=0;i<4;i++) temp[i] = SBOX[temp[i]];
	GET_UINT32(T, temp, 0);
	return T;
}

//	--------------------------------------------------------------------

uint32 RotWord(uint32 T)
{
	// returneaza uint32 - ul rotat

	return((T << 8) | (T >> 24));
}

//	--------------------------------------------------------------------

void KeyExpansion(uint8 key[], uint32 W[], int NK)
{
	// completeaza cu NK chei array-ul W[] pornind de la cheia key[]

	uint32 temp; int i;
	for(i=0; i<NK; i++)
        GET_UINT32(W[i], key, 4*i);
    for(i=NK; i<NB*(NR+1);i++){
        temp= W[i-1];
        if(i%NK==0)
            temp=SubWord(RotWord(temp))^RCON[(i-4)/NK];
        else if((NK>6) && (i%NK==4))
            temp=SubWord(temp);
        W[i]=W[i-NK]^temp;
    }


}

//	--------------------------------------------------------------------

uint8 xtime(uint8 aa)
{
	// returneaza rezultatul operatiei inmultirii byte-ului aa cu x
	//returns x.aa={02}.aa;
	if(0x80&aa) return (aa<<1) ^ 0x1B;

	else return aa<<1;
}

//	--------------------------------------------------------------------

void init_xtim()
{
	// completeaza array-ul XTIME[256][8]
	int j, k, idx=1;
	for(j=0;j<256;j++) XTIM[j][0]=j;
	for(k=1;k<8;k++)
        for(j=0;j<256;j++)
        XTIM[j][k]=xtime(XTIM[j][k-1]);
}

//	--------------------------------------------------------------------

uint8 dot(uint8 aa, uint8 bb)
{
	// returneaza rezultatul operatiei aa * bb ( * - operatia dot)
	uint8 sum=0;
	int idx=1;
	for(int k=0;k<8;k++){
	    if(aa&idx) sum = sum ^ XTIM[bb][k];
	    idx=idx*2;
	}
	return sum;
}

//	--------------------------------------------------------------------

void init_state(uint8 in[], uint8 stat[])
{
	// initializeaza stat[] cu valorile din in[]

	for(int i=0;i<NB*4;i++)
        stat[i]=in[i];
}

//	--------------------------------------------------------------------

void display_b(uint8 stat[])
{
	for (int j=0; j<NB*4; j++)
	{
		printf("%3x", stat[j]);
	}
	printf("\n");
}

//	--------------------------------------------------------------------

void AddRoundKey(uint8 stat[], uint32 W[], int round)
{
	// adauga cheia din W[] pentru roundul round in stat[]

	uint8 TEMP[4];
	for(int j=0;j<NB;j++)
    {
        PUT_UINT32(W[ NB*round +j], TEMP,0);
        for(int k=0;k<4;k++)
            stat[j*4+k]=stat[j*4+k]^TEMP[k];
    }
}

//	--------------------------------------------------------------------

void SubBytes(uint8 stat[])
{
	// aplica sandbox-ul

	for(int j=0;j<NB*4;j++)
        stat[j]=SBOX[stat[j]];
}

//	--------------------------------------------------------------------

void ShiftRows(uint8 stat[])
{
	// shifteaza liniile din stat[] conform specificatiei AES(to the left)
	//stat looks like
        //0 4  8 12
        //1 5 9 13
        //2 6 10 14
        //3 7 11 15

        uint8 TEMP;
        //shift second row by  1 pos to the left
        TEMP=stat[1];
        stat[1]=stat[5];
        stat[5]=stat[9];
        stat[9]=stat[13];
        stat[13]=TEMP;

        //shift third row by 2 positions to the left
        TEMP=stat[2];
        stat[2]=stat[10];
        stat[10]=TEMP;
        TEMP=stat[6];
        stat[6]=stat[14];
        stat[14]=TEMP;

        //shift 4th row 3 pos=right by 1 pos

        TEMP=stat[15];
        stat[15]=stat[11];
        stat[11]=stat[7];
        stat[7]=stat[3];
        stat[3]=TEMP;

}

//	--------------------------------------------------------------------

void MixColumns(uint8 stat[])
{
	// mixare coloane din stat[] conform specificatiei AES

	//MULTIPLY MATRIX 02 03 01 01
                    //01 02 03 01
                    //01 01 02 03
                    //03 01 01 02

    //WITH STAT
    int j;
    uint8 temp[NB*4];
    for(j=0;j<NB*4;j++)
        temp[j]=stat[j];
    for(j=0;j<NB;j++)
    {
        stat[j*4+0]=dot(0x02, temp[j*4])^ dot(0x03, temp[j*4+1])^temp[j*4+2]^temp[j*4+3];
        stat[j*4+1]=temp[j*4]^ dot(0x02, temp[j*4+1]) ^ dot(0x03, temp[j*4+2])^temp[j*4+3];
        stat[j*4+2]=temp[j*4]^ temp[j*4+1]^dot(0x02, temp[j*4+2]) ^ dot(0x03, temp[j*4+3]);
        stat[j*4+3]=dot(0x03,temp[j*4])^ temp[j*4+1]^temp[j*4+2] ^ dot(0x02,  temp[j*4+3]);
    }
}

//	--------------------------------------------------------------------

void InvSubBytes(uint8 stat[])
{
	// aplica inv sandbox-ul
}

//	--------------------------------------------------------------------

void InvShiftRows(uint8 stat[])
{
	// shifteaza liniile din stat[] conform specificatiei AES
}

//	--------------------------------------------------------------------

void InvMixColumns(uint8 stat[])
{
	// mixare coloane din stat[] conform specificatiei AES
}

//	====================================================================

int main(int argc, char* argv[])
{
	int i, NK[3];
	uint8 state[NB*4];
	uint32 WW[NB * (1 + MAX_NUM_ROUNDS)];

	//	----------------------------------------------------------------
	//	init phase
	//	----------------------------------------------------------------

	static uint8 key4[16] =
	{
		0x2b, 0x7e, 0x15, 0x16, 0x28, 0xae, 0xd2, 0xa6,
		0xab, 0xf7, 0x15, 0x88, 0x09, 0xcf, 0x4f, 0x3c
	};

	static uint8 key6[24] =
	{
		0x8e, 0x73, 0xb0, 0xf7, 0xda, 0x0e, 0x64, 0x52,
		0xc8, 0x10, 0xf3, 0x2b, 0x80, 0x90, 0x79, 0xe5,
		0x62, 0xf8, 0xea, 0xd2, 0x52, 0x2c, 0x6b, 0x7b
	};

	static uint8 key8[32] =
	{
		0x60, 0x3d, 0xeb, 0x10, 0x15, 0xca, 0x71, 0xbe,
		0x2b, 0x73, 0xae, 0xf0, 0x85, 0x7d, 0x77, 0x81,
		0x1f, 0x35, 0x2c, 0x07, 0x3b, 0x61, 0x08, 0xd7,
		0x2d, 0x98, 0x10, 0xa3, 0x09, 0x14, 0xdf, 0xf4
	};

	init_xtim();

	//	----------------------------------------------------------------

	//	APPENDIX A - key schedules for 128, 192, and 256 bit keys

	for (int ii=2; ii>=0; --ii)
	{
		NK[ii] = 4 + ii*2;
		NR = NK[ii] + 6;

		if (ii==0)
			KeyExpansion(key4, WW, NK[ii]);
		else if (ii==1)
			KeyExpansion(key6, WW, NK[ii]);
		else
			KeyExpansion(key8, WW, NK[ii]);
	}

	//	----------------------------------------------------------------

	//	APPENDIX B - cipher phase for test_appb - 128 bits key from key4

	NR = NK[0] + 6;
	init_state(test_appb, state);
	display_b(state);

	AddRoundKey(state, WW, 0);	// WW contains the key schedule for key4

	for (i=1; i<NR; i++)
	{
		SubBytes(state);
		display_b(state);
		ShiftRows(state);
		display_b(state);
		MixColumns(state);
		display_b(state);
		AddRoundKey(state, WW, i);
		display_b(state);
	}

	SubBytes(state);
	display_b(state);
	ShiftRows(state);
	display_b(state);
	AddRoundKey(state, WW, NR);

	display_b(state);
	getchar();

	//	----------------------------------------------------------------

	//	APPENDIX C - cipher, decipher phase for test_appc using all keys

	for (int ii=0; ii<3; ii++)
	{
		NR = NK[ii] + 6;
		KeyExpansion(keys_appc, WW, NK[ii]);

		//	cipher phase - like APPENDIX B, from init_state() to the end

		//	decipher phase (inverse cipher only) - similar to APPENDIX B
	}

	return 0;
}

//	********************************************************************
