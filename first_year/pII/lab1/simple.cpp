#include <iostream>
#include <string.h>
#include <stdlib.h>

using namespace std;

#define MAX_LEN 10000


bool check_number(int nr) {
    return nr > 0;
}

int sum(int nr) {
    int sum = 0;

    while(nr) {
        sum += (nr % 10) * (nr % 10);
        nr /= 10;
    }

    return sum;
}

int reverse(int nr) {
    int n = 0;

    while(nr) {
        n = n * 10 + nr % 10;
        nr /= 10;
    }

    return n;
}

char *strrev(char *p){
    char *q;
    q = (char *) malloc(strlen(p));
    int n = strlen(p), i;

    for(i=0; i<=n; i++) {
        q[i] = p[n-i-1];
    }
    q[i] = '\0';

    return q;
}

char *substraction(char a[]) {
    int i=0, lena=strlen(a) - 1, k=0;
    a = strrev(a);

    while (k <= lena && a[k] == '0') {
        a[k++] = '9';
    }

    a[k] = a[k] - 1;
    if (a[k] == '0') {
        a[k] = '\0';
    }

    return strrev(a);
}

char *multiplication(char a[], char b[]) {
    int lena=strlen(a) - 1, lenb=strlen(b) - 1, i, j, k=0, r, x=0, y, sum;
    char temp[MAX_LEN];
    char c[MAX_LEN];
    char *mul;
    mul = (char *) malloc(MAX_LEN);

    for(i=lenb; i >=0 ; i--) {
        r = 0;

        for(j=lena; j >= 0; j--) {
            temp[k++] = ((b[i] - 48) * (a[j] - 48) + r) % 10;
            r = ((b[i] - 48) * (a[j] - 48) + r)/10;
        }

        x++;
        for(y=0; y < x; y++){
            temp[k++] = 0;
        }
    }

    k = 0;
    r = 0;
    for(i=0; i<lena + lenb + 1; i++){
         sum = 0;
         y = 0;
         for(j=1; j<=lenb + 1; j++){
             if(i <=lena + j){
                 sum = sum + temp[y+i];
             }
             y += j + lena + 1;
         }
         c[k++] = (sum + r) %10;
         r = (sum + r)/10;
    }
    c[k] = r;
    j = 0;

    for(i=k-1; i>=0; i--){
         mul[j++] = c[i] + 48;
    }

    mul[j]='\0';
    return mul;
}

int factorial(int n) {
    int factorial = 1;

    while(n) {
        factorial *= n;
        n -= 1;
    }

    return factorial;
}

int interval(int x) {
    if (x < -3) return x - 3;
    if (x >= -3 && x <= 3) return 4 * x * x - 2 * x + 1;
    return x + 3;
}

char *big_factorial(char n[]) {
    char *result = (char *) malloc(MAX_LEN);
    strcpy(result, n);

    n = substraction(n);
    while (strcmp(n, "1") != 0) {
        cout<<n<<" "<<result<<endl;
        result = multiplication(result, n);
        n = substraction(n);
    }

    return result;
}

int main() {
    int n;
    char big_number[500];

    //cin>>n;
    //cin>>big_number;
    //cout<<multiplication("30", "2");
    cout<<big_factorial("5");

    //cout<<"sum: "<<sum(n)<<endl;
    //cout<<"reverse: "<<reverse(n)<<endl;
    //cout<<"factorial: "<<factorial(n)<<endl;
    //cout<<"factorial: "<<factorial_big(big_number)<<endl;
    //cout<<"interval: "<<interval(n)<<endl;

    return 0;
}
