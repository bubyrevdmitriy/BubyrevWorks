// mesurmentsResult.h
#include <string>
#include <sstream>
#include <string>
#include <vector>
#pragma once /* Защита от двойного подключения заголовочного файла */
using namespace std;
class mesurmentsResult {
public:
	mesurmentsResult(int numberOfMesurments, std::vector<double> mesurmentsArrayEnter)
	{
		this->numberOfMesurments = numberOfMesurments;	
		this->mesurmentsArrayEnter = mesurmentsArrayEnter;
	}

	void set_mesurmentsArrayEnterAverage() {
	
		double mesurmentsArrayEnterSumm = 0;
		double mesurmentsArrayEnterAverage;
		//int numberOfMesurments = get_numberOfMesurments();

		for (int i = 0; i < numberOfMesurments; i++)
		{
			mesurmentsArrayEnterSumm = mesurmentsArrayEnterSumm + mesurmentsArrayEnter[i];
		}
		mesurmentsArrayEnterAverage = mesurmentsArrayEnterSumm / numberOfMesurments;

		this -> mesurmentsArrayEnterAverage = mesurmentsArrayEnterAverage;
	}

	void set_mesurmentsArrayDeviations() {

		std::vector<double> mesurmentsArrayDeviations;
		for (int i = 0; i < numberOfMesurments; i++)
		{
			mesurmentsArrayDeviations.push_back(mesurmentsArrayEnter[i]- mesurmentsArrayEnterAverage);
		}
		this->mesurmentsArrayDeviations = mesurmentsArrayDeviations;
	}

	void set_mesurmentsArrayDeviationsSquared() 
	{
		std::vector<double> mesurmentsArrayDeviationsSquared;
		for (int i = 0; i < numberOfMesurments; i++)
		{
			mesurmentsArrayDeviationsSquared.push_back(mesurmentsArrayDeviations[i] * mesurmentsArrayDeviations[i]);
		}
		this->mesurmentsArrayDeviationsSquared = mesurmentsArrayDeviationsSquared;
	}

	void set_mesurmentsArrayDeviationsSquaredSumm() {
		double mesurmentsArrayDeviationsSquaredSumm = 0;
		for (int i = 0; i < numberOfMesurments; i++)
		{
			mesurmentsArrayDeviationsSquaredSumm = mesurmentsArrayDeviationsSquaredSumm + mesurmentsArrayDeviationsSquared[i];
		}
		this->mesurmentsArrayDeviationsSquaredSumm = mesurmentsArrayDeviationsSquaredSumm;
	}

	void set_standardDeviation() {
		double standardDeviation;
		standardDeviation = sqrt(mesurmentsArrayDeviationsSquaredSumm/(numberOfMesurments - 1));
		this->standardDeviation = standardDeviation;
	}

	void makeBeatifulMesurmentsResult() {
		set_mesurmentsArrayEnterAverage();
		set_mesurmentsArrayDeviations();
		set_mesurmentsArrayDeviationsSquared();
		set_mesurmentsArrayDeviationsSquaredSumm();
		set_standardDeviation();
	}

protected:
	int numberOfMesurments;
	std::vector<double> mesurmentsArrayEnter;
	double mesurmentsArrayEnterAverage;
	std::vector<double> mesurmentsArrayDeviations;
	std::vector<double> mesurmentsArrayDeviationsSquared;
	double mesurmentsArrayDeviationsSquaredSumm;
	double standardDeviation;
	std::vector<double> mesurmentsArrayResult;
	double mesurmentsResultResult;
	int numberOfMesurmentsResult;
};