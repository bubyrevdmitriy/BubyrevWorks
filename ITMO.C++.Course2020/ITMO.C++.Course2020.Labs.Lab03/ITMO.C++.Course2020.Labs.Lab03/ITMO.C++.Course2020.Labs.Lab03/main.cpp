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
	cout << "Требуется вычислить корень n-й степени из числа a, используя вещественный бинарный поиск" << endl;
	cout << "a – действительное, больше или равное 1, не превосходит 1000, задано с точностью до 6 знаков после десятичной точки:" << endl;
	cin >> a;
	cout << "n – натуральное, не превосходящее 10:" << endl;
	cin >> n;
	double r = firBinSearch(a, n);

	cout << "Корень из числа "<<a<<" степени "<< n <<":"<< r << endl;
	
	return 0;
}*/

/*int addNumders(int n)
{
	if (n == 1) return 1; // выход из рекурсии
	else return (n + addNumders(n - 1));
}

int main()
{
	system("chcp 1251");
	int n;
	cout << "n – Целое положительное число:" << endl;
	cin >> n;

	int r = addNumders(n);
	cout << "Сумма чисел от 1 до "<< n <<" равняется: "<< r << endl;

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
	cout << "Первое целое положительное число:" << endl;
	cin >> m;
	cout << "Второе целое положительное число:" << endl;
	cin >> n;
	r = gcd(m, n);
	cout << "Наибольший делитель чисел " << m <<" и "<< n << " является: " << r << endl;

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
	//вершины выпуклого многоугольника
	const int n = 5;////////////////////5
	cout << "Вычисление площади выпуклого пятиугольника" << endl;
	int arrX[n];
	int arrY[n];
	

	cout << "Далее вводятся координаты "<<n<<" точек, например:" << endl;
	cout << " Точка №1(0,0), точка №2(2,4), точка №3(6,5), точка №4(10,3), точка №5(11,-1)" << endl;

	for (int i = 0; i < n; i++) {
		cout << "Введите значение координаты X точки №" << i + 1 << ":\n";
		cin >> arrX[i];
		cout << "\nВведите значение координаты Y точки №" << i + 1 << ":\n";
		cin >> arrY[i];
	}


	Point points[n]{ Point(arrX[0],arrY[0]), Point(arrX[1],arrY[1]), Point(arrX[2],arrY[2]), Point(arrX[3],arrY[3]), Point(arrX[4],arrY[4]) };

	float square = 0;
	//перебор треугольников
	for (int i = 1; i < n - 1; i++) {
		square += TriangleSquare(points[0], points[i], points[i + 1]);
	}
	cout << "Площадь составит: " << square << endl;
	system("pause");
	return 0;
}

float TriangleSquare(Point& A, Point& B, Point& C) {
	float AB = Distance(A, B);
	float BC = Distance(B, C);
	float AC = Distance(A, C);
	float p = (AB + BC + AC) / 2;               //половина периметра
	return sqrt(p * (p - AB) * (p - BC) * (p - AC));  //Герон
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
	double num;//начальное число
	int rootDegree = 3;//требуемая степень корня
	//---
	double eps = 0.00001;             //допустимая погрешность
	double root;   //начальное приближение корня
	double rn;                  //значение корня последовательным делением
	int countiter = 0;                //число итераций
	//---
	double resultStandart;//результат по стандартному методу
	double resultCustom;//результат по итерационнуму методу
	
		
	cout << "Вычисление кубического корня" << endl;
	cout << "Ваедите исходное число:" << endl;
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
	
	

	cout << "Кубический корень, вычисленный встроенным методом: " << resultStandart << endl;
	cout << "Кубический корень, вычисленный итерационным методом: " << resultCustom << endl;

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

	cout << "Первая сторона треугольника:" << endl;
	cin >> a;
	cout << "Вторая сторона треугольника:" << endl;
	cin >> b;
	cout << "Третья сторона треугольника:" << endl;
	cin >> c;

	if ( (a==b)&&(a==c) ) {
		s= TriangleArea(a);
		cout << "Треугольник равносторонний" << endl;
	} else {
		s= TriangleArea(a,b,c);
		cout << "Треугольник не равносторонний" << endl;
	}

	cout << "Площадь треугольника составляет; " << s << endl;

	return 0;
}*/

//------------------------------------
//4
/*int addFiveNumders(int n)
{
	if (n == 1) return 5; // выход из рекурсии
	else return (5*n + addFiveNumders(n - 1));
}

int main()
{
	system("chcp 1251");
	int n;
	cout << "Вычисляем сумму ряда S = 5 + 10 + 15 + … + 5·n," << endl;
	cout << "n – Целое положительное число:" << endl;
	cin >> n;

	int r = addFiveNumders(n);
	cout << "Сумма ряда чисел" << n << " равняется: " << r << endl;

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
	cout << "Переводим число в двоичную систему" << endl;
	cout << "n – Целое положительное число:" << endl;
	cin >> n;
	cout << "Число "<< n <<" в двиочной системе сотавит: " << endl;
	toBin(n);
	return 0;
}*/
//------------------------------------