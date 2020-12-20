#include <iostream>
#include <string>
#include<math.h>
using namespace std;
/*int main()
{
	string name;
	cout << "What is your name? (Task01)";
	//cin >> name;
	getline(cin, name);
	cout << "Hello, " << name << "!\n";
}*/

/*int main()
{
	system("chcp 1251");
	cout.precision(3);
	double x;
	double a, b; cout << "\n¬ведите a и b:\n";
	cin >> a; // ввод с клавиатуры значени€ a
	cin >> b; // ввод с клавиатуры значени€ b
	x = a / b; // вычисление значени€ x
	cout << "\nx = " << x << endl; //вывод результата на экран
	cout << sizeof(a / b) << ends << sizeof(x) << endl;
	return 0;
}*/

/*int main()
{
	system("chcp 1251");
	string name;
	cout << "¬ведите свое им€";
	double x;
	double a, b;
	cout << "\n¬ведите a и b:\n";
	cin >> a;
	cin >> name;
	cin >> b;
	x = a / b;
	cout << "\nx = " << x << endl;
	cout << "ѕривет, " << name << "!\n";
	return 0;
}*/

/*int main()
{
	system("chcp 1251");
	double S, p;
	double a, b, c;
	cout << "¬ведите значение стороны a:\n";	
	cin >> a;
	cout << "\n¬ведите значение стороны b:\n";
	cin >> b;
	cout << "\n¬ведите значение стороны c:\n";
	cin >> c;
	p = a + b + c;
	S= sqrt(p*(p-a)*(p-b)*(p-c)) ;
	cout << "\np = " << p << endl;
	cout << "\nS = " << S << "\n";
	return 0;
}*/

int main()
{
	system("chcp 1251");
	double Apent;
	double x1, y1, x2, y2, x3, y3, x4, y4, x5, y5 ;
	cout << "¬ведите значение координаты X точки є1:\n";
	cin >> x1;
	cout << "\n¬ведите значение координаты Y точки є1:\n";
	cin >> y1;
	cout << "\n¬ведите значение координаты X точки є2:\n";
	cin >> x2;
	cout << "\n¬ведите значение координаты Y точки є2:\n";
	cin >> y2;
	cout << "\n¬ведите значение координаты X точки є3:\n";
	cin >> x3;
	cout << "\n¬ведите значение координаты Y точки є3:\n";
	cin >> y3;
	cout << "\n¬ведите значение координаты X точки є4:\n";
	cin >> x4;
	cout << "\n¬ведите значение координаты Y точки є4:\n";
	cin >> y4;
	cout << "\n¬ведите значение координаты X точки є5:\n";
	cin >> x5;
	cout << "\n¬ведите значение координаты Y точки є5:\n";
	cin >> y5;

	Apent = 0.5*abs(x1*y2+x2*y3+x3*y4+x4*y5+x5*y1-x2*y1-x3*y2-x4*y3-x5*y4-x1*y5);

	cout << "\nApent = " << Apent << endl;
	
	return 0;
}