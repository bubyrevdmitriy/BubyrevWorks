#include <iostream>
#include <string>
#include <cmath> 

//#include "pch.h"
//#include <stdafx.h>
#include "stdafx.h"
#include <Windows.h>
#include <math.h>


using namespace std;

/*void privet(string name)
{
	cout << name << ", " << "hello!" << endl;
}

int main()
{
	string name;
	cout << "What is your name?" << endl;
	cin >> name;
	privet(name);
	return 0;
}*/

/*string privet(string name)
{
	string str = name + ", " + "hello!\n";
	return str;
}

void privet(string name, int k)
{
	cout << name << ", " << "hello! " << "you input " << k << endl;
}

int main()
{
	string name;
	int k;
	cout << "What is your name?" << endl;
	cin >> name;
	string nameOut = privet(name);
	cout << nameOut << endl;
	cout << "Input number:" << endl;
	cin >> k;
	privet(name, k);
	return 0;
}*/

/*long double firBinSearch(double a, int n)
{
	double L = 0;
	double R = a;
	while (R - L > 1e-10)
	{
		double M = (L + R) / 2;
		if (pow(M, n) < a)
		{
			L = M;
		}
		else
		{
			R = M;
		}
	}
	return R;
}

int main()
{
	system("chcp 1251");
	double a;
	double n;
	cout << "��������� ��������� ������ n-� ������� �� ����� a, ��������� ������������ �������� �����" << endl;
	cout << "a � ��������������, ������ ��� ������ 1, �� ����������� 1000, ������ � ��������� �� 6 ������ ����� ���������� �����:" << endl;
	cin >> a;
	cout << "n � �����������, �� ������������� 10:" << endl;
	cin >> n;
	double r = firBinSearch(a, n);

	cout << "������ �� ����� "<<a<<" ������� "<< n <<":"<< r << endl;
	
	return 0;
}*/

/*int addNumders(int n)
{
	if (n == 1) return 1; // ����� �� ��������
	else return (n + addNumders(n - 1));
}

int main()
{
	system("chcp 1251");
	int n;
	cout << "n � ����� ������������� �����:" << endl;
	cin >> n;

	int r = addNumders(n);
	cout << "����� ����� �� 1 �� "<< n <<" ���������: "<< r << endl;

	return 0;
}*/

/*int gcd(int m, int n)
{
	if (n == 0) return m;
	return gcd(n, m % n);
}

int main()
{
	system("chcp 1251");
	int m;
	int n;
	int r;
	cout << "������ ����� ������������� �����:" << endl;
	cin >> m;
	cout << "������ ����� ������������� �����:" << endl;
	cin >> n;
	r = gcd(m, n);
	cout << "���������� �������� ����� " << m <<" � "<< n << " ��������: " << r << endl;

	return 0;
}*/
//--------------------------------------
//1
class Point {
	//VARIABLES
public:
	int x;
	int y;
	//CONSTRUCTORS
public:
	Point(int x, int y) {
		this->x = x;
		this->y = y;
	}
};

float TriangleSquare(Point& A, Point& B, Point& C);
float Distance(Point& A, Point& B);

int main()
{
	system("chcp 1251");
	//������� ��������� ��������������
	const int n = 5;////////////////////5
	cout << "���������� ������� ��������� �������������" << endl;
	int arrX[n];
	int arrY[n];
	

	cout << "����� �������� ���������� "<<n<<" �����, ��������:" << endl;
	cout << " ����� �1(0,0), ����� �2(2,4), ����� �3(6,5), ����� �4(10,3), ����� �5(11,-1)" << endl;

	for (int i = 0; i < n; i++) {
		cout << "������� �������� ���������� X ����� �" << i + 1 << ":\n";
		cin >> arrX[i];
		cout << "\n������� �������� ���������� Y ����� �" << i + 1 << ":\n";
		cin >> arrY[i];
	}


	Point points[n]{ Point(arrX[0],arrY[0]), Point(arrX[1],arrY[1]), Point(arrX[2],arrY[2]), Point(arrX[3],arrY[3]), Point(arrX[4],arrY[4]) };

	float square = 0;
	//������� �������������
	for (int i = 1; i < n - 1; i++) {
		square += TriangleSquare(points[0], points[i], points[i + 1]);
	}
	cout << "������� ��������: " << square << endl;
	system("pause");
	return 0;
}

float TriangleSquare(Point& A, Point& B, Point& C) {
	float AB = Distance(A, B);
	float BC = Distance(B, C);
	float AC = Distance(A, C);
	float p = (AB + BC + AC) / 2;               //�������� ���������
	return sqrt(p * (p - AB) * (p - BC) * (p - AC));  //�����
}

float Distance(Point& A, Point& B) {
	return sqrt(pow(A.x - B.x, 2) + pow(A.y - B.y, 2));
}





//------------------------------------
//2
/*double powThreeStandart(double num, int rootDegree)
{
	double s;
	s = pow(num, 1.0 / rootDegree);
	return s;
}
double powThreeCustom(double num)
{
	double s=0;
	//s = ;
	return s;
}
double mabs(double x) { return (x < 0) ? -x : x; }
int main()
{
	system("chcp 1251");
	double num;//��������� �����
	int rootDegree = 3;//��������� ������� �����
	//---
	double eps = 0.00001;             //���������� �����������
	double root;   //��������� ����������� �����
	double rn;                  //�������� ����� ���������������� ��������
	int countiter = 0;                //����� ��������
	//---
	double resultStandart;//��������� �� ������������ ������
	double resultCustom;//��������� �� ������������� ������
	
		
	cout << "���������� ����������� �����" << endl;
	cout << "������� �������� �����:" << endl;
	cin >> num;
	root = num / rootDegree;
	rn = num;

	resultStandart = powThreeStandart(num, rootDegree);
	
	//---------------------------
	while (mabs(root - rn) >= eps) {
		rn = num;
		for (int i = 1; i < rootDegree; i++) {
			rn = rn / root;
		}
		root = 0.5 * (rn + root);
		countiter++;
	}
		resultCustom = rn;
	//---------------------------
	
	

	cout << "���������� ������, ����������� ���������� �������: " << resultStandart << endl;
	cout << "���������� ������, ����������� ������������ �������: " << resultCustom << endl;

	return 0;
}*/

//------------------------------------
//3
/*double TriangleArea(double a)
{
	double s;
	s = ( sqrt(3) )*a*a/4;
	return s;
}
double TriangleArea(double a, double b, double c)
{
	double s;
	double p;
	p = a+b+c;
	s = sqrt(p*(p-a)*(p-b)*(p-c));
	return s;
}
int main()
{
	system("chcp 1251");
	double a;
	double b;
	double c;
	double s;

	cout << "������ ������� ������������:" << endl;
	cin >> a;
	cout << "������ ������� ������������:" << endl;
	cin >> b;
	cout << "������ ������� ������������:" << endl;
	cin >> c;

	if ( (a==b)&&(a==c) ) {
		s= TriangleArea(a);
		cout << "����������� ��������������" << endl;
	} else {
		s= TriangleArea(a,b,c);
		cout << "����������� �� ��������������" << endl;
	}

	cout << "������� ������������ ����������; " << s << endl;

	return 0;
}*/

//------------------------------------
//4
/*int addFiveNumders(int n)
{
	if (n == 1) return 5; // ����� �� ��������
	else return (5*n + addFiveNumders(n - 1));
}

int main()
{
	system("chcp 1251");
	int n;
	cout << "��������� ����� ���� S = 5 + 10 + 15 + � + 5�n," << endl;
	cout << "n � ����� ������������� �����:" << endl;
	cin >> n;

	int r = addFiveNumders(n);
	cout << "����� ���� �����" << n << " ���������: " << r << endl;

	return 0;
}*/
//------------------------------------
//5
/*void toBin(int n)
{
	if (n >= 2) {
		toBin(n / 2);
	} 
	cout << n % 2;
}

int main() 
{
	system("chcp 1251");
	int n;
	//string r;
	cout << "��������� ����� � �������� �������" << endl;
	cout << "n � ����� ������������� �����:" << endl;
	cin >> n;
	cout << "����� "<< n <<" � �������� ������� �������: " << endl;
	toBin(n);
	return 0;
}*/
//------------------------------------