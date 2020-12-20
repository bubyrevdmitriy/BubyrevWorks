#include <iostream>
#include <string>
using namespace std;
class TriangleComposition
{
public:
	TriangleComposition(double x1, double y1, double x2, double y2, double x3, double y3)
	{
		this->DotPrivate1 = new DotPrivate(x1, y1);
		this->DotPrivate2 = new DotPrivate(x2, y2);
		this->DotPrivate3 = new DotPrivate(x3, y3);
	}

	double get_side1()
	{
		return DotPrivate1->distanceTo(DotPrivate2);
	}

	double get_side2()
	{
		return DotPrivate2->distanceTo(DotPrivate3);
	}

	double get_side3()
	{
		return DotPrivate3->distanceTo(DotPrivate1);
	}

	void set_p()
	{
		p = get_side1() + get_side2() + get_side3();
	}

	double get_p()
	{
		return p;
	}

	void set_S()
	{
		//p = get_side1() + get_side2() + get_side3();
		double pH = p / 2;
		S = sqrt(pH * (pH - get_side1()) * (pH - get_side2()) * (pH - get_side3()));
	}

	double get_S()
	{
		return S;
	}

private:
	class DotPrivate
	{
	public:
		double x;
		double y;

		DotPrivate() {
			this->x = 0;
			this->y = 0;
		}

		DotPrivate(double x, double y) {
			this->x = x;
			this->y = y;
			
		}

		double get_x() {
			return x;
		}

		double get_y() {
			return y;
		}

		void set_x(double x) {
			this->x = x;
		}

		void set_y(double y) {
			this->y = y;
		}

		double distanceTo(DotPrivate* point)
		{
			return sqrt(pow(point->get_x() - x, 2) + pow(point->get_y() - y, 2));
		}

		double distanceTo(DotPrivate point)
		{
			return sqrt(pow(point.get_x() - x, 2) + pow(point.get_y() - y, 2));
		}
	};


	// точка №1
	DotPrivate* DotPrivate1;
	// точка №2
	DotPrivate* DotPrivate2;
	// точка №3
	DotPrivate* DotPrivate3;
	// Пепиметр
	double p;
	// Площадь 
	double S;

};


int main()
{
	system("chcp 1251");
	cout << "Создание класса-агрегатора" << endl;
	double x1, x2, x3;
	double y1, y2, y3;
	cout << "Введите координату Х точки №1: ";
	cin >> x1;
	cout << "Введите координату Y точки №1: ";
	cin >> y1;
	cout << "Введите координату Х точки №2: ";
	cin >> x2;
	cout << "Введите координату Y точки №2: ";
	cin >> y2;
	cout << "Введите координату Х точки №3: ";
	cin >> x3;
	cout << "Введите координату Y точки №3: ";
	cin >> y3;
	
	
	TriangleComposition* TriangleComposition1 = new TriangleComposition(x1, y1, x2, y2, x3, y3);

	cout << "Первая сторона треугольника: " << TriangleComposition1->get_side1() << endl;
	cout << "Вторая сторона треугольника: " << TriangleComposition1->get_side2() << endl;
	cout << "Третья сторона треугольника: " << TriangleComposition1->get_side3() << endl;

	TriangleComposition1->set_p();
	cout << "Периметр треугольника: " << TriangleComposition1->get_p() << endl;

	TriangleComposition1->set_S();
	cout << "Площадь треугольника: " << TriangleComposition1->get_S() << endl;


	return 0;
}
