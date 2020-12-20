// teacher.h
#include "human.h"
#include <string>
class teacher : public human {
	// Конструктор класса teacher
public:
	teacher(
		std::string last_name,
		std::string name,
		std::string second_name,
		// Количество учебных часов за семестр у преподавателя
		unsigned int work_time
	) : human(
		last_name,
		name,
		second_name
	) {
		this->work_time = work_time;
	}

	teacher() : human() {}

	// Получение количества учебных часов
	unsigned int get_work_time()
	{
		return this->work_time;
	}
	void getdata()
	{
		human::getdata();
		std::cout << "Введите work time : ";
		std::cin >> work_time;
	}
	 void putdata()
	{
		std::cout << "\nClass teacher. ";
		human::putdata();
		std::cout << "\nWork time:" << work_time;
	}
private:
	// Учебные часы
	unsigned int work_time;
};