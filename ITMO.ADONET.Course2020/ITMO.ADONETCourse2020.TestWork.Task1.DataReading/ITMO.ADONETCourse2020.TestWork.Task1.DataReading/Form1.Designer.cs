namespace ITMO.ADONETCourse2020.TestWork.Task1.DataReading
{
    partial class Form1
    {
        /// <summary>
        /// Обязательная переменная конструктора.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Освободить все используемые ресурсы.
        /// </summary>
        /// <param name="disposing">истинно, если управляемый ресурс должен быть удален; иначе ложно.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Код, автоматически созданный конструктором форм Windows

        /// <summary>
        /// Требуемый метод для поддержки конструктора — не изменяйте 
        /// содержимое этого метода с помощью редактора кода.
        /// </summary>
        private void InitializeComponent()
        {
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.лаботаСБДToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.подключитьсяКБазеДанныхToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.отключитьсяОтБазыДанныхToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.асинхронноеПодключениеКБДToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.списокПодключенийToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.listView1 = new System.Windows.Forms.ListView();
            this.DepartentName = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.DepartmentID = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Budget = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.StartDate = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Administrator = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.button1 = new System.Windows.Forms.Button();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.listView2 = new System.Windows.Forms.ListView();
            this.Title = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.columnHeader7 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.columnHeader8 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Credits = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.button2 = new System.Windows.Forms.Button();
            this.button3 = new System.Windows.Forms.Button();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.button4 = new System.Windows.Forms.Button();
            this.button5 = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.menuStrip1.SuspendLayout();
            this.groupBox1.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.groupBox3.SuspendLayout();
            this.SuspendLayout();
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.лаботаСБДToolStripMenuItem,
            this.асинхронноеПодключениеКБДToolStripMenuItem,
            this.списокПодключенийToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(733, 24);
            this.menuStrip1.TabIndex = 0;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // лаботаСБДToolStripMenuItem
            // 
            this.лаботаСБДToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.подключитьсяКБазеДанныхToolStripMenuItem,
            this.отключитьсяОтБазыДанныхToolStripMenuItem});
            this.лаботаСБДToolStripMenuItem.Name = "лаботаСБДToolStripMenuItem";
            this.лаботаСБДToolStripMenuItem.Size = new System.Drawing.Size(150, 20);
            this.лаботаСБДToolStripMenuItem.Text = "Работа с Базой Данных ";
            this.лаботаСБДToolStripMenuItem.Click += new System.EventHandler(this.лаботаСБДToolStripMenuItem_Click);
            // 
            // подключитьсяКБазеДанныхToolStripMenuItem
            // 
            this.подключитьсяКБазеДанныхToolStripMenuItem.Name = "подключитьсяКБазеДанныхToolStripMenuItem";
            this.подключитьсяКБазеДанныхToolStripMenuItem.Size = new System.Drawing.Size(239, 22);
            this.подключитьсяКБазеДанныхToolStripMenuItem.Text = "Подключиться к Базе Данных";
            this.подключитьсяКБазеДанныхToolStripMenuItem.Click += new System.EventHandler(this.подключитьсяКБазеДанныхToolStripMenuItem_Click);
            // 
            // отключитьсяОтБазыДанныхToolStripMenuItem
            // 
            this.отключитьсяОтБазыДанныхToolStripMenuItem.Name = "отключитьсяОтБазыДанныхToolStripMenuItem";
            this.отключитьсяОтБазыДанныхToolStripMenuItem.Size = new System.Drawing.Size(239, 22);
            this.отключитьсяОтБазыДанныхToolStripMenuItem.Text = "Отключиться от Базы Данных";
            this.отключитьсяОтБазыДанныхToolStripMenuItem.Click += new System.EventHandler(this.отключитьсяОтБазыДанныхToolStripMenuItem_Click);
            // 
            // асинхронноеПодключениеКБДToolStripMenuItem
            // 
            this.асинхронноеПодключениеКБДToolStripMenuItem.Name = "асинхронноеПодключениеКБДToolStripMenuItem";
            this.асинхронноеПодключениеКБДToolStripMenuItem.Size = new System.Drawing.Size(200, 20);
            this.асинхронноеПодключениеКБДToolStripMenuItem.Text = "Асинхронное подключение к БД";
            this.асинхронноеПодключениеКБДToolStripMenuItem.Click += new System.EventHandler(this.асинхронноеПодключениеКБДToolStripMenuItem_Click);
            // 
            // списокПодключенийToolStripMenuItem
            // 
            this.списокПодключенийToolStripMenuItem.Name = "списокПодключенийToolStripMenuItem";
            this.списокПодключенийToolStripMenuItem.Size = new System.Drawing.Size(140, 20);
            this.списокПодключенийToolStripMenuItem.Text = "Список подключений";
            this.списокПодключенийToolStripMenuItem.Click += new System.EventHandler(this.списокПодключенийToolStripMenuItem_Click);
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.listView1);
            this.groupBox1.Controls.Add(this.button1);
            this.groupBox1.Location = new System.Drawing.Point(13, 28);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(340, 321);
            this.groupBox1.TabIndex = 1;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Получение списка направлений обучения";
            // 
            // listView1
            // 
            this.listView1.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.DepartentName,
            this.DepartmentID,
            this.Budget,
            this.StartDate,
            this.Administrator});
            this.listView1.HideSelection = false;
            this.listView1.Location = new System.Drawing.Point(7, 20);
            this.listView1.Name = "listView1";
            this.listView1.Size = new System.Drawing.Size(327, 266);
            this.listView1.TabIndex = 1;
            this.listView1.UseCompatibleStateImageBehavior = false;
            this.listView1.View = System.Windows.Forms.View.Details;
            this.listView1.SelectedIndexChanged += new System.EventHandler(this.listView1_SelectedIndexChanged);
            // 
            // DepartentName
            // 
            this.DepartentName.Text = "DepartentName";
            // 
            // DepartmentID
            // 
            this.DepartmentID.Text = "DepartmentID";
            this.DepartmentID.Width = 86;
            // 
            // Budget
            // 
            this.Budget.Text = "Budget";
            // 
            // StartDate
            // 
            this.StartDate.Text = "StartDate";
            // 
            // Administrator
            // 
            this.Administrator.Text = "Administrator";
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(6, 292);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(328, 23);
            this.button1.TabIndex = 0;
            this.button1.Text = "Получить список направлений обучения";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.listView2);
            this.groupBox2.Controls.Add(this.button2);
            this.groupBox2.Location = new System.Drawing.Point(359, 28);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(362, 321);
            this.groupBox2.TabIndex = 2;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Получение списка предметов";
            // 
            // listView2
            // 
            this.listView2.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.Title,
            this.columnHeader7,
            this.columnHeader8,
            this.Credits});
            this.listView2.HideSelection = false;
            this.listView2.Location = new System.Drawing.Point(1, 20);
            this.listView2.Name = "listView2";
            this.listView2.Size = new System.Drawing.Size(355, 266);
            this.listView2.TabIndex = 1;
            this.listView2.UseCompatibleStateImageBehavior = false;
            this.listView2.View = System.Windows.Forms.View.Details;
            this.listView2.SelectedIndexChanged += new System.EventHandler(this.listView2_SelectedIndexChanged);
            // 
            // Title
            // 
            this.Title.Text = "Title";
            // 
            // columnHeader7
            // 
            this.columnHeader7.Text = "CourseID";
            // 
            // columnHeader8
            // 
            this.columnHeader8.Text = "DepartmentID";
            // 
            // Credits
            // 
            this.Credits.Text = "Credits";
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(6, 292);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(350, 23);
            this.button2.TabIndex = 0;
            this.button2.Text = "Получить список предметов";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // button3
            // 
            this.button3.Location = new System.Drawing.Point(646, 523);
            this.button3.Name = "button3";
            this.button3.Size = new System.Drawing.Size(75, 23);
            this.button3.TabIndex = 3;
            this.button3.Text = "Exit";
            this.button3.UseVisualStyleBackColor = true;
            this.button3.Click += new System.EventHandler(this.button3_Click);
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.label2);
            this.groupBox3.Controls.Add(this.label1);
            this.groupBox3.Controls.Add(this.button5);
            this.groupBox3.Controls.Add(this.button4);
            this.groupBox3.Location = new System.Drawing.Point(20, 365);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(695, 129);
            this.groupBox3.TabIndex = 4;
            this.groupBox3.TabStop = false;
            this.groupBox3.Text = "Хранимые процедуры";
            this.groupBox3.Enter += new System.EventHandler(this.groupBox3_Enter);
            // 
            // button4
            // 
            this.button4.Location = new System.Drawing.Point(7, 30);
            this.button4.Name = "button4";
            this.button4.Size = new System.Drawing.Size(178, 40);
            this.button4.TabIndex = 0;
            this.button4.Text = "Количество направлений (Departments)";
            this.button4.UseVisualStyleBackColor = true;
            this.button4.Click += new System.EventHandler(this.button4_Click);
            // 
            // button5
            // 
            this.button5.Location = new System.Drawing.Point(7, 76);
            this.button5.Name = "button5";
            this.button5.Size = new System.Drawing.Size(178, 38);
            this.button5.TabIndex = 1;
            this.button5.Text = "Количество предметов (Course)";
            this.button5.UseVisualStyleBackColor = true;
            this.button5.Click += new System.EventHandler(this.button5_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(208, 44);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(204, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "Количество направлений (Departments)";
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(211, 87);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(166, 13);
            this.label2.TabIndex = 3;
            this.label2.Text = "Количество предметов (Course)";
            this.label2.Click += new System.EventHandler(this.label2_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(733, 558);
            this.Controls.Add(this.groupBox3);
            this.Controls.Add(this.button3);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.groupBox1.ResumeLayout(false);
            this.groupBox2.ResumeLayout(false);
            this.groupBox3.ResumeLayout(false);
            this.groupBox3.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.ToolStripMenuItem лаботаСБДToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem асинхронноеПодключениеКБДToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem списокПодключенийToolStripMenuItem;
        private System.Windows.Forms.ListView listView1;
        private System.Windows.Forms.ToolStripMenuItem подключитьсяКБазеДанныхToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem отключитьсяОтБазыДанныхToolStripMenuItem;
        private System.Windows.Forms.ListView listView2;
        private System.Windows.Forms.Button button3;
        private System.Windows.Forms.ColumnHeader DepartentName;
        private System.Windows.Forms.ColumnHeader DepartmentID;
        private System.Windows.Forms.ColumnHeader Budget;
        private System.Windows.Forms.ColumnHeader StartDate;
        private System.Windows.Forms.ColumnHeader Administrator;
        private System.Windows.Forms.ColumnHeader Title;
        private System.Windows.Forms.ColumnHeader columnHeader7;
        private System.Windows.Forms.ColumnHeader columnHeader8;
        private System.Windows.Forms.ColumnHeader Credits;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.Button button5;
        private System.Windows.Forms.Button button4;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
    }
}

