/*#include <iostream>
#include <string>
#include <sstream>
using namespace std;
class Triangle
{
public:

	class ExScore //����� ����������
	{
	public:
		string origin; //��� ����� �������
		double iValue; //��� �������� ���������� ��������

		ExScore(string originEnter, double iValueEnter)
		{
			origin = originEnter; //������ � ������ ��������� ������
			iValue = iValueEnter; //����������� ������������ ��������
		}
	};


	// ��������� ������ ������������
	void set_sides(double aEnter, double bEnter, double cEnter)
	{
		
		if (aEnter > (aEnter+ bEnter)) {
			throw ExScore("� ������� set_sides()", aEnter);
		}
		if (bEnter > (aEnter + cEnter)) {
			throw ExScore("� ������� set_sides()", bEnter);
		}
		if (cEnter > (aEnter + bEnter)) {
			throw ExScore("� ������� set_sides()", cEnter);
		}

		a = aEnter;
		b = bEnter;
		c = cEnter;
	}

	// ��������� ������� ������������ a
	double get_a()
	{
		return a;
	}
	// ��������� ������� ������������ b
	double get_b()
	{
		return b;
	}
	// ��������� ������� ������������ c
	double get_c()
	{
		return c;
	}

	// ��������� ��������� ����� ���������� � ������������
	string get_info()
	{
		std::stringstream ss;
		//put arbitrary formatted data into the stream
		ss <<"a=" << a << ", b=" << b << ", c=" << c;

		//convert the stream buffer into a string
		std::string str = ss.str();

		return str;
	}

	// ��������� ��������� ������������
	double get_p()
	{
		return p;
	}
	// ��������� ������� ������������
	double get_S()
	{
		return S;
	}

	// ��������� ��������� ������������
	void set_p()
	{
		p = a + b + c;
	}
	// ��������� ������� ������������
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
	// �������� ������� ������ Student
	Triangle triangle01;
	double a, b, c;
	double S, p;

	cout << "������� �������� ������� a:\n";
	cin >> a;
	cout << "\n������� �������� ������� b:\n";
	cin >> b;
	cout << "\n������� �������� ������� c:\n";
	cin >> c;
	
	try
	{
		triangle01.set_sides(a, b, c);
		triangle01.set_p();
		triangle01.set_S();

		cout << "\n������������ �����������:\n";
		cout << "\n" << triangle01.get_info() << "\n";
		cout << "\n�������� �����������a:\n";
		cout << "\n" << triangle01.get_p() << "\n";
		cout << "\n������� �����������a:\n";
		cout << "\n" << triangle01.get_S() << "\n";
	}
	catch (Triangle::ExScore& ex)
	{
		cout << "\n������ ������������� " << ex.origin;
		cout << "\n��������� �������� ������ " << ex.iValue << " �������� ������������\n";
	}

	

	return 0;
}*/