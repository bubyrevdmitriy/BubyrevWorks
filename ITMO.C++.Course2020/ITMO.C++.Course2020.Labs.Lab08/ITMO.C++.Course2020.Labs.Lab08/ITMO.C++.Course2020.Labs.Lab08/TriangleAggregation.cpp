/* student.cpp */
#include <string>
#include <fstream>
#include "TriangleAggregation.h"
#include <sstream>

void TriangleAggregation::setDot1(Dot* dot)
{
	dot1 = dot;
}
Dot TriangleAggregation::getDot1()
{
	return *dot1;
}

void TriangleAggregation::setDot2(Dot* dot)
{
	dot2 = dot;
}
Dot TriangleAggregation::getDot2()
{
	return *dot2;
}

void TriangleAggregation::setDot3(Dot* dot)
{
	dot3 = dot;
}
Dot TriangleAggregation::getDot3()
{
	return *dot3;
}

TriangleAggregation::TriangleAggregation(Dot* dot1, Dot* dot2, Dot* dot3)
{
	TriangleAggregation::setDot1(dot1);
	TriangleAggregation::setDot2(dot2);
	TriangleAggregation::setDot3(dot3);
}

double TriangleAggregation::get_side1()
{
	return dot1->distanceTo(dot2);
}

double TriangleAggregation::get_side2()
{
	return dot2->distanceTo(dot3);
}

double TriangleAggregation::get_side3()
{
	return dot3->distanceTo(dot1);
}

void TriangleAggregation::set_p()
{
	p = get_side1() + get_side2() + get_side3();
}

double TriangleAggregation::get_p()
{
	return p;
}

void TriangleAggregation::set_S()
{
	//p = get_side1() + get_side2() + get_side3();
	double pH = p / 2;
	S = sqrt(pH * (pH - get_side1()) * (pH - get_side2()) * (pH - get_side3()));
}

double TriangleAggregation::get_S()
{
	return S;
}


TriangleAggregation::~TriangleAggregation()
{
	TriangleAggregation::save();
}
// Запись данных о студенте в файл
void TriangleAggregation::save()
{
	ofstream fout("TriangleAggregation.txt", ios::app);
	fout << "Стороны треугольника: "<<TriangleAggregation::get_side1() << ", "
		<< TriangleAggregation::get_side2() << ", "
		<< TriangleAggregation::get_side3();
	
	fout << endl;
	fout.close();
}




