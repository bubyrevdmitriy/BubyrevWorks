/* point.h */
#pragma once /* Защита от двойного подключения заголовочного файла */
#include <string>
//#include <int>
using namespace std;
class Point
{
public:
	Point();
	Point(double, double);

	void set_x(double);
	double get_x();

	void set_y(double);
	double get_y();

	double get_length();

	friend std::ostream& operator<< (std::ostream& out, const Point& dist);
	bool  operator< (const Point&) const;

private:
	double x;
	double y;	
};