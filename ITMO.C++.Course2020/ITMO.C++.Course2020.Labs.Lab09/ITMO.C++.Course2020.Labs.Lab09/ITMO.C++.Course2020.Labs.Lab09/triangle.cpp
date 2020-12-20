/*#include <iostream>
#include <string>
#include <sstream>
using namespace std;
class Triangle
{
public:

	class ExScore //класс исключений
	{
	public:
		string origin; //для имени функции
		double iValue; //для хранения ошибочного значения

		ExScore(string originEnter, double iValueEnter)
		{
			origin = originEnter; //строка с именем виновника ошибки
			iValue = iValueEnter; //сохраненное неправильное значение
		}
	};


	// Установка сторон треугольника
	void set_sides(double aEnter, double bEnter, double cEnter)
	{
		
		if (aEnter > (aEnter+ bEnter)) {
			throw ExScore("в функции set_sides()", aEnter);
		}
		if (bEnter > (aEnter + cEnter)) {
			throw ExScore("в функции set_sides()", bEnter);
		}
		if (cEnter > (aEnter + bEnter)) {
			throw ExScore("в функции set_sides()", cEnter);
		}

		a = aEnter;
		b = bEnter;
		c = cEnter;
	}

	// Получение стороны треугольника a
	double get_a()
	{
		return a;
	}
	// Получение стороны треугольника b
	double get_b()
	{
		return b;
	}
	// Получение стороны треугольника c
	double get_c()
	{
		return c;
	}

	// Получение получение общей информации о треугольнике
	string get_info()
	{
		std::stringstream ss;
		//put arbitrary formatted data into the stream
		ss <<"a=" << a << ", b=" << b << ", c=" << c;

		//convert the stream buffer into a string
		std::string str = ss.str();

		return str;
	}

	// Получение периметра треугольника
	double get_p()
	{
		return p;
	}
	// Получение площади треугольника
	double get_S()
	{
		return S;
	}

	// Получение периметра треугольника
	void set_p()
	{
		p = a + b + c;
	}
	// Получение площади треугольника
	void set_S()
	{
		S = sqrt(p * (p - a) * (p - b) * (p - c));
	}

private:
	double a, b, c;
	double S, p;	
};


int main()
{
	system("chcp 1251");
	// Создание объекта класса Student
	Triangle triangle01;
	double a, b, c;
	double S, p;

	cout << "Введите значение стороны a:\n";
	cin >> a;
	cout << "\nВведите значение стороны b:\n";
	cin >> b;
	cout << "\nВведите значение стороны c:\n";
	cin >> c;
	
	try
	{
		triangle01.set_sides(a, b, c);
		triangle01.set_p();
		triangle01.set_S();

		cout << "\nПолучившийся тркугольник:\n";
		cout << "\n" << triangle01.get_info() << "\n";
		cout << "\nПериметр тркугольникa:\n";
		cout << "\n" << triangle01.get_p() << "\n";
		cout << "\nПлощадь тркугольникa:\n";
		cout << "\n" << triangle01.get_S() << "\n";
	}
	catch (Triangle::ExScore& ex)
	{
		cout << "\nОшибка инициализации " << ex.origin;
		cout << "\nВведенное значение оценки " << ex.iValue << " является недопустимым\n";
	}

	

	return 0;
}*/