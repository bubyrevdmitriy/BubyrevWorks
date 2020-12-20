#include <iostream>
#include <windows.h>

using namespace std;
//-----------------------------------------
/*void fum_value(double k, double x, double y)//������� ���������
{
	x = x + k;
	y = y + k;
}
void fum_ptr(double k, double* x, double* y)//��������� ���������
{
	*x = *x + k;
	*y = *y + k;
}
void fum_ref(double k, double &x, double &y)//��������� ������
{
	x = x + k;
	y = y + k;
}
void print(double x, double y)
{
	cout << "x = " << x << "; y = " << y << endl;
}

int main()
{
	system("chcp 1251");
	//cout << "Hello World!" << endl;

	double k = 2.5;
	double xv = 10;
	double yv = 10;

	cout << "�������� ���������" << endl;
	print(xv, yv);

	cout << "�������� � ������� �������� ���������" << endl;
	fum_value(k, xv, yv); // �������� � ������� �������� ���������
	
	print(xv, yv);

	cout << "�������� � ������� ��������� ���������" << endl;
	fum_ptr(k, &xv, &yv); // �������� � ������� ��������� ���������
	print(xv, yv);
	
	cout << "�������� � ������� ��������� ������" << endl;
	fum_ref(k, xv, yv); // �������� � ������� ��������� ������
	print(xv, yv);

	return 0;
}*/

//----------------------------------------
/*void swap(int*, int*);//���������
void swap(int&, int&);//������

void print(double x, double y)//������
{
	cout << "x = " << x << "; y = " << y << endl;
}
void swap(int* x, int* y)//���������
{
	int temp;
	temp = *x;
	*x = *y;
	*y = temp;
}
void swap(int& x, int& y)//������
{
	int temp;
	temp = x;
	x = y;
	y = temp;
}

int main()
{
	system("chcp 1251");
	//cout << "Hello World!" << endl;

	int x = 5;
	int y = 10;

	cout << "�������� ���������" << endl;
	print(x, y);

	cout << "�������� � ������� ��������� ���������" << endl;
	swap(&x, &y); // �������� � ������� ��������� ���������
	print(x, y);

	cout << "�������� � ������� ��������� ������" << endl;
	swap(x, y); // �������� � ������� ��������� ������
	print(x, y);

	return 0;
}*/
//-----------------------------------------------------------
//1
int Myroot(double, double, double, double&, double&);

int Myroot(double a, double b, double c, double& x1, double& x2) {
	
	double d = b*b-4*a*c;
	//cout << "d = " << d << endl;

	if (d >= 0) {
		x1=(-b+sqrt(d))/(2*a);
		x2=(-b-sqrt(d))/(2*a);
		//cout << "x1 = " << x1 << endl;
		//cout << "x2 = " << x2 << endl;
	}

	if (d == 0) {
		return 0;
	}
	else {
		if (d > 0) {
			return 1;
		} else {//d<0
			return -1;
		}
	}

	return 1;
}



int main()
{
	system("chcp 1251");
	cout << "������� ������ ������ ����������� ���������" << endl;
	double a, b, c, x1, x2;

	cout << "a = "; cin >> a;
	cout << "b = "; cin >> b;
	cout << "c = "; cin >> c;


	x1 = 0;
	x2 = 0;

	int result = Myroot(a, b, c, x1, x2);
	
	if (result == 0) {
		cout << "������������ ����� 0, � ��������� ���� 1 ������" << endl;
		cout << "x = "<< x1 << endl;
	}
	else {
		if (result > 0) {
			cout << "������������ ������ 0, � ��������� ���� 2 �����" << endl;
			cout << "x1 = " << x1 << endl;
			cout << "x2 = " << x2 << endl;
		}
		else {//d<0
			cout << "������������ ������ 0, � ��������� ��� ������" << endl;
		}
	}

	return 0;
}

//-----------------------------------------------------------
//2
/*
bool Input(int&, int&);//������


bool Input(int& a, int& b)//������
{
	cout << "������� ����� � ����������� ������" << endl;
	cout << "����������, ������� ����� ������ 10, ��� �����!" << endl;
	cout << "a = "; cin >> a;
	cout << "b = "; cin >> b;

	if (a<10 && b<10) {
		return true;
	}
	else {
		return false;
	}
}

int main()
{
	system("chcp 1251");
	int a, b;
	if (Input(a, b) == false) // if(!Input(a,b))
	{
		cerr << "error";
		return 1;
	}
	int s = a + b;
	cout << "���������: "<< s <<" = " << a <<" + "<< b << endl;
	return 0;
}*/

//-----------------------------------------------------------