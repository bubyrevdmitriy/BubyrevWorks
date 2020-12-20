#pragma once
#include <string>
using namespace std;
class Dot
{
private:
	double x;
	double y;
public:

	double get_x();
	double get_y();

	Dot();
	Dot(double x, double y);
	double distanceTo(Dot* point);
};