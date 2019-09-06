package student;


import inventory.Equipment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gurjot273
 */

//JFrame fro displaying course details of the student passed in constructor
public class DisplayCourseDetailsGUI extends javax.swing.JFrame {

    /**
     * Creates new form DisplayCourseDetailsGUI
     */
    private Student student;
    private boolean flag;
    private static final String rootUsername="root";
    private static final String rootPassword="password";
    private static final String conn_string="jdbc:mysql://localhost:3306/UDIS";
    private Connection conn=null;
    
    public DisplayCourseDetailsGUI(Student student,boolean flag) {//flag determines whether ds or student is accessing it 
        this.student=student;
        this.flag=flag;
        new StudentCourseInformation(student).calculateGPA();//calcutes the GPA
        initComponents();
        getContentPane().setBackground(new java.awt.Color(200, 247, 197));
        ArrayList<Equipment> List;
        List = new ArrayList<>();
        Equipment e1;
        //adding rows to the table
        DefaultTableModel model= (DefaultTableModel)jTable2.getModel();
        try
        {
            conn= DriverManager.getConnection(conn_string, rootUsername, rootPassword);//connecting to database
            Statement st=(Statement) conn.createStatement();
            String query="SELECT * FROM ds";
            ResultSet rs=st.executeQuery(query);//executes the query
            rs.next();
            int semester=rs.getInt("semester");
            int year=rs.getInt("ongoingYear");
            query="SELECT * FROM students where rollNumber='"+student.rollNumber+"'";
            rs=st.executeQuery(query);
            rs.next(); 
            int studentId=rs.getInt("id");
            jTextField1.setText(""+(float)(Math.round(rs.getFloat("CGPA")*100.0)/100.0));//rounding CGPA to decimal places
            query="SELECT * FROM courses WHERE studentId='"+studentId+"' AND year='"+year+"' AND semester='"+semester+"' AND grade IS NULL";
            rs=st.executeQuery(query);//finding the required courses
            Object[] row=new Object[5];
            int numRows=0;
            if(!rs.next()){
            }else{
                do{
                    numRows++;
                    row[0]=numRows;
                    row[1]=rs.getString("name");
                    row[2]=rs.getString("courseId");
                    if(rs.getInt("semester")==1){
                        row[3]="SPRING "+rs.getInt("year");
                    }else{
                        row[3]="AUTUMN "+rs.getInt("year");
                    }
                    row[4]=rs.getString("credits");
                    model.addRow(row);//adding rows
                }while(rs.next());
            }
            
            model= (DefaultTableModel)jTable3.getModel();
            query="SELECT * FROM courses WHERE studentId='"+studentId+"' AND grade='F' ORDER BY year,semester";
            rs=st.executeQuery(query);
            numRows=0;
            if(!rs.next()){
            }else{
                do{
                    numRows++;
                    row[0]=numRows;
                    row[1]=rs.getString("name");
                    row[2]=rs.getString("courseId");
                    if(rs.getInt("semester")==1){
                        row[3]="SPRING "+rs.getInt("year");
                    }else{
                        row[3]="AUTUMN "+rs.getInt("year");
                    }
                    row[4]=rs.getString("credits");
                    model.addRow(row);
                }while(rs.next());
            }
            
            model= (DefaultTableModel)jTable4.getModel();
            query="SELECT * FROM courses WHERE studentId='"+studentId+"' AND grade!='F' AND grade IS NOT NULL ORDER BY year,semester";
            rs=st.executeQuery(query);
            numRows=0;
            row=new Object[6];
            if(!rs.next()){
            }else{
                do{
                    numRows++;
                    row[0]=numRows;
                    row[1]=rs.getString("name");
                    row[2]=rs.getString("courseId");
                    if(rs.getInt("semester")==1){
                        row[3]="SPRING "+rs.getInt("year");
                    }else{
                        row[3]="AUTUMN "+rs.getInt("year");
                    }
                    row[4]=rs.getString("credits");
                    row[5]=rs.getString("grade");
                    model.addRow(row);
                }while(rs.next());
            }
        }
        catch(SQLException e)
        {
            System.err.println(e); 
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("STUDENT COURSE INFORMATION");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("BACKLOG COURSES");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("COMPLETED COURSES");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "SL NO", "COURSE NAME", "COURSE ID","SEMESTER","CREDITS"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "SL NO", "COURSE NAME", "COURSE ID","SEMESTER","CREDITS"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "SL NO", "COURSE NAME", "COURSE ID","SEMESTER","CREDITS","GRADE"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setText("ONGOING COURSES");

        jLabel5.setText("CGPA");

        jTextField1.setEditable(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jButton2.setText("X");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(273, 273, 273)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 341, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 206, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(232, 232, 232)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(291, 291, 291)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        new StudentGui(this.student,flag).setVisible(true);//displaying the previous page
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new DisplayCourseDetailsGUI(new Student(),false).setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
