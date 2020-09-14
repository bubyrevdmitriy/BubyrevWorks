using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace DatasetDesigner
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void GetCustomersButton_Click(object sender, EventArgs e)
        {
            try
            {
            Northwind: NorthwindDataSet NorthwindDataset1 = new NorthwindDataSet();//a.Создайте экземпляр типизированного набора данных

            CustomersTableAdapter: NorthwindDataSetTableAdapters.CustomersTableAdapter
               CustomersTableAdapter1 = new NorthwindDataSetTableAdapters.CustomersTableAdapter();//b.Создайте экземпляр класса

                CustomersTableAdapter1.Fill(NorthwindDataset1.Customers);// c.Вызовите метод для загрузки всех клиентов в DataTable:

                foreach (NorthwindDataSet.CustomersRow NWCustomer in// d.Передайте значения столбца CompanyName в ListBox:
               NorthwindDataset1.Customers.Rows)
                {
                    CustomersListBox.Items.Add(NWCustomer.CompanyName);
                }
            }
            catch
            {
                MessageBox.Show("Ошибка!");
            }


        }
    }
}
