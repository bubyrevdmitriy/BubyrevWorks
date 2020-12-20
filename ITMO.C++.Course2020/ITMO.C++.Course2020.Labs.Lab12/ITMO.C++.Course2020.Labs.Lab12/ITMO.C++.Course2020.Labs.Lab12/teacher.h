// teacher.h
#include "human.h"
#include <string>
class teacher : public human {
	// ����������� ������ teacher
public:
	teacher(
		std::string last_name,
		std::string name,
		std::string second_name,
		// ���������� ������� ����� �� ������� � �������������
		unsigned int work_time
	) : human(
		last_name,
		name,
		second_name
	) {
		this->work_time = work_time;
	}

	teacher() : human() {}

	// ��������� ���������� ������� �����
	unsigned int get_work_time()
	{
		return this->work_time;
	}
	void getdata()
	{
		human::getdata();
		std::cout << "������� work time : ";
		std::cin >> work_time;
	}
	 void putdata()
	{
		std::cout << "\nClass teacher. ";
		human::putdata();
		std::cout << "\nWork time:" << work_time;
	}
private:
	// ������� ����
	unsigned int work_time;
};