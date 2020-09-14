namespace ITMO.WinFormCourse2020.Labs.Lab01.Exercise05_02
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
            this.ButtonExitForm2 = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // ButtonExitForm2
            // 
            this.ButtonExitForm2.Location = new System.Drawing.Point(330, 193);
            this.ButtonExitForm2.Name = "ButtonExitForm2";
            this.ButtonExitForm2.Size = new System.Drawing.Size(119, 45);
            this.ButtonExitForm2.TabIndex = 0;
            this.ButtonExitForm2.Text = "GREENPEACE";
            this.ButtonExitForm2.UseVisualStyleBackColor = true;
            this.ButtonExitForm2.Click += new System.EventHandler(this.ButtonExitForm2_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Green;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.ButtonExitForm2);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button ButtonExitForm2;
    }
}

