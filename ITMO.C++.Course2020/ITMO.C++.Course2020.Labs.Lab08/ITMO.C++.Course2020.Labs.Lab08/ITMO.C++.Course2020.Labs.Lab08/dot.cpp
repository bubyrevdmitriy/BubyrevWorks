#include "dot.h"
#include <math.h>
Dot::Dot()
{
	x = 0; y = 0;
}
Dot::Dot(double x, double y)
{
	this->x = x;
	this->y = y;
}

double Dot::get_x()
{
	return x;
}

double Dot::get_y()
{
	return y;
}

double Dot::distanceTo(Dot* point)
{
	return sqrt(pow(point->get_x() - x, 2) + pow(point->get_y() - y, 2) );
}