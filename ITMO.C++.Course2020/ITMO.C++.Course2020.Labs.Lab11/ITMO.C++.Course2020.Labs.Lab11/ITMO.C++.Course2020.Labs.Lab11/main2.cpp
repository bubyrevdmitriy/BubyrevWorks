/*#include <iostream>
#include <string>
#include <vector>
using namespace std;

struct StudentGrade
{
	string name;
	char grade;
};

class GradeMap
{
private:
	vector<StudentGrade> m_map;
public:
	GradeMap()
	{ }
	char& operator[](const string& name);
};


char& GradeMap::operator[](const string& name)
{
	// Найдём ли мы имя ученика в векторе
	for (auto& ref : m_map)
	{
		// Если нашли, то возвращаем ссылку на его оценку
		if (ref.name == name)
			return ref.grade;
	}
	// Не нашли - создаём новый StudentGrade для нового ученика
	StudentGrade temp{ name, ' ' };
	// Помещаем его в конец вектора
	m_map.push_back(temp);
	// И возвращаем ссылку на его оценку
	return m_map.back().grade;
}

int main()
{
	GradeMap grades;
	grades["John"] = 'A';
	grades["Martin"] = 'B';
	cout << "John has a grade of " << grades["John"] << endl;
	cout << "Martin has a grade of " << grades["Martin"] << endl;
	cout << "New name and grade?" << endl;
	string name;
	char grade;
	cin >> name >> grade;
	grades[name] = grade;
	std::cout << name << " has a grade of " << grades[name] << endl;
	return 0;
}*/





