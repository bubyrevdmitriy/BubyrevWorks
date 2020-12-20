// student.h
#include "human.h"
#include <string>
#include <vector>
class student : public human {
public:
	// Конструктор класса Student
	student(std::string last_name, std::string name, std::string second_name,
		std::vector<int> scores) : human(last_name, name, second_name) {
		this->scores = scores;
	}

	student() : human() {}
	
	// Получение среднего балла студента
	float get_average_score()
	{
		// Общее количество оценок
		unsigned int count_scores = this->scores.size();
		// Сумма всех оценок студента
		unsigned int sum_scores = 0;
		// Средний балл
		float average_score;
		for (unsigned int i = 0; i < count_scores; ++i) {
			sum_scores += this->scores[i];
		}
		average_score = (float)sum_scores / (float)count_scores;
		return average_score;
	}


	void getdata()
	{
		human::getdata();
		std::vector<int> scores;
		int mark1, mark2, mark3, mark4, mark5;
		// Добавление оценок студента в вектор
		std::cout << "Введите оценку №1 : ";
		std::cin >> mark1;
		scores.push_back(mark1);
		std::cout << "Введите оценку №2 : ";
		std::cin >> mark2;
		scores.push_back(mark2);
		std::cout << "Введите оценку №3 : ";
		std::cin >> mark3;
		scores.push_back(mark3);
		std::cout << "Введите оценку №4 : ";
		std::cin >> mark4;
		scores.push_back(mark4);
		std::cout << "Введите оценку №5 : ";
		std::cin >> mark5;
		scores.push_back(mark5);
		this->scores = scores;
		
	}

	 void putdata()
	{
		std::cout << "\nClass student. ";
		human::putdata();
		std::cout << "\nAverage score:" << get_average_score();
	}
private:
	// Оценки студента
	std::vector<int> scores;
};