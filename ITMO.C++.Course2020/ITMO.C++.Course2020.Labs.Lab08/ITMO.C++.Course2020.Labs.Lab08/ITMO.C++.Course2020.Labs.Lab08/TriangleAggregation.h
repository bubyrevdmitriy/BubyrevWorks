/* time.h */
#pragma once /* Защита от двойного подключения заголовочного файла */
#include <string>
//#include <int>
#include "C:/Users/bubyr/source/repos/ITMO.C++.Course2020/ITMO.C++.Course2020.Labs.Lab08/ITMO.C++.Course2020.Labs.Lab08/ITMO.C++.Course2020.Labs.Lab08/dot.h"
using namespace std;
class TriangleAggregation//нежесткое включение классов
{
public:

	void setDot1(Dot* dot);
	Dot getDot1();

	void setDot2(Dot* dot);
	Dot getDot2();

	void setDot3(Dot* dot);
	Dot getDot3();

	double get_side1();
	double get_side2();
	double get_side3();

	// 
	void set_p();
	//
	double get_p();

	// У
	void set_S();
	// 
	double get_S();

	// Конструктор класса Triange
	TriangleAggregation(Dot* dot1, Dot* dot2, Dot* dot3);

	// Запись данных о классе в файл
	void save();
	// Деструктор класса time
	~TriangleAggregation();

private:
	// точка №1
	Dot* dot1;
	// точка №2
	Dot* dot2;
	// точка №3
	Dot* dot3;
	// Пепиметр
	double p;
	// Площадь 
	double S;
};