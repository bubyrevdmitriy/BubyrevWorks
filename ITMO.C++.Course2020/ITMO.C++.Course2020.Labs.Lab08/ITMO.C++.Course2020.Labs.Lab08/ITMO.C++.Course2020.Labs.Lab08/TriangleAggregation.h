/* time.h */
#pragma once /* ������ �� �������� ����������� ������������� ����� */
#include <string>
//#include <int>
#include "C:/Users/bubyr/source/repos/ITMO.C++.Course2020/ITMO.C++.Course2020.Labs.Lab08/ITMO.C++.Course2020.Labs.Lab08/ITMO.C++.Course2020.Labs.Lab08/dot.h"
using namespace std;
class TriangleAggregation//��������� ��������� �������
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

	// �
	void set_S();
	// 
	double get_S();

	// ����������� ������ Triange
	TriangleAggregation(Dot* dot1, Dot* dot2, Dot* dot3);

	// ������ ������ � ������ � ����
	void save();
	// ���������� ������ time
	~TriangleAggregation();

private:
	// ����� �1
	Dot* dot1;
	// ����� �2
	Dot* dot2;
	// ����� �3
	Dot* dot3;
	// ��������
	double p;
	// ������� 
	double S;
};