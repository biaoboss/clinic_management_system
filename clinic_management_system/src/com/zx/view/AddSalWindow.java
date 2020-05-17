package com.zx.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.zx.control.AddSalAction;
import com.zx.dao.ProListDao;
import com.zx.dao.impl.ProListDaoImpl;
import com.zx.view.common.CenterWindow;
import com.zx.vo.ProListVo;

/**
 * 
 * @author 彭文大帅比
 *
 */
public class AddSalWindow extends JDialog {
	
	private AddSalWindow window;
	
//	public AddSalWindow getAddSalWindow() {
//		if(window == null) {
//			window = new AddSalWindow();
//		}
//		return window;
//	}
	
	private JSplitPane splitpane;
//					面板左边、右边的panel,查询的panel,清单的panel,已选择项目的panel
	private JPanel leftpanel,rightpanel,fondpanel,listpanel,selectpanel,
//	病种类型 ,按键，医嘱panel
	classpanel,btnpanel,notepanel;
	//               左table        右table
	DefaultTableModel listmodel , promodel ;
	
	Vector listname , proname;
//	               项目清单表     选择项目表
	  JTable listtable, protable;
//	                    查询文本框
	private JTextField fondtxt;
//	           医嘱
	private JTextArea adviceArea;
//             病种多选框	
	private JComboBox classbox;
//	                            查找按钮          修改按钮    删除按钮        确认按钮         退出按钮     
	private JButton fondbtn,modifybtn,deletebtn,comnfirmbtn,exitbtn;
	
	ProListDao listdao;
	
	ProListVo listVo;
	
//	增加收费窗口
	ShowBasicInfomationPane showPane;
	public AddSalWindow(ShowBasicInfomationPane showPane) {
		this.showPane = showPane;
		fondpanel = new JPanel();
		listpanel  = new JPanel();
		selectpanel = new JPanel();
		classpanel = new JPanel();
		btnpanel = new JPanel();
		notepanel = new JPanel();
		
		fondtxt = new JTextField(10);
		
//		构造项目清单表格
		listname = new Vector();
		listname.add("项目编号");
		listname.add("项目名称");
		listname.add("单位");
		listname.add("规格");
		listname.add("项目价格");
		listname.add("项目类型");
		listname.add("库存量");
		listdao = new ProListDaoImpl();
		listmodel = new DefaultTableModel(listdao.getProList() , listname) {
			public boolean isCellEditable(int rowIdeax, int columnIdeax) {
				return false;
			}
		};
		listtable = new JTable(listmodel);
		adviceArea = new JTextArea(3,50);
//		构造按键并添加动作监听器
		fondbtn = new JButton("查询");
		fondbtn.addActionListener(new AddSalAction(this,showPane));
		modifybtn = new JButton("修改");
		modifybtn.addActionListener(new AddSalAction(this,showPane));
		deletebtn = new JButton("删除");
		deletebtn.addActionListener(new AddSalAction(this,showPane));
		comnfirmbtn = new JButton("确认");
		comnfirmbtn.addActionListener(new AddSalAction(this,showPane));
		exitbtn = new JButton("退出");
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		exitbtn.addActionListener(new AddSalAction(this,showPane));
//		给listtable添加鼠标事件监听器
		listtable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
//				得到鼠标点击次数
				int count = me.getClickCount();
				if(count == 2) {
					int row = listtable.getSelectedRow();
					
					int column = listtable.getSelectedColumn();
					
					
					
					String id = (String) listtable.getValueAt(row, 0);
					String name = (String) listtable.getValueAt(row, 1);
					double price = (double) listtable.getValueAt(row, 4);
					String type = (String) listtable.getValueAt(row, 5);
					ProListVo listVo = new ProListVo(id, name, price, type);
//					添加服务到收费单窗口
					new AddAcrossWindow(AddSalWindow.this,listVo);
				}
			}
		});
		
//		构造已选择项目表格
		String [] name = {"项目编号","项目名称","数量","项目价格","类型"};
		proname = new Vector();
		
		proname.add("项目编号");
		proname.add("项目名称");
		proname.add("数量");
		proname.add("项目价格");
		proname.add("类型");
		Object [][] pro = {};
		promodel = new DefaultTableModel(pro,name);
		protable = new JTable(promodel) {
		    public boolean isCellEditable(int rowIndex, int columnIndex) {
	            
	            return false;
	        }

		};
		
//		给fondpanel添加值
		fondpanel.add(new JLabel("输入项目编号或名称查询项目:"));
		fondpanel.add(fondtxt);
		fondpanel.add(fondbtn);
		fondpanel.setBorder(BorderFactory.createTitledBorder("查询项目信息"));
		
//		查询下方的项目清单表格
		listpanel.add(new JScrollPane(listtable));
		btnpanel.setLayout(new GridLayout(1,4));
		String [] pname = {"心脑血管病","呼吸系统疾病","血液系统疾病","免疫系统疾病"};
		classbox = new JComboBox(pname);
		classpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		classpanel.add(new JLabel("病种类型："));
		classpanel.add(classbox);
		classpanel.add(btnpanel);
		btnpanel.add(modifybtn);
		btnpanel.add(deletebtn);
		btnpanel.add(comnfirmbtn);
		btnpanel.add(exitbtn);
		notepanel.setLayout(new FlowLayout());
		notepanel.add(new JLabel("医嘱信息:"));
		notepanel.add(adviceArea);
		
//		右方已选择项目表格
		selectpanel.setLayout(new BorderLayout());
		selectpanel.add(new JScrollPane(protable),BorderLayout.NORTH);
		selectpanel.add(classpanel);
		selectpanel.setBorder(BorderFactory.createTitledBorder("已选择的项目"));
		
	
		
		this.setTitle("增加收费");
		this.setModal(true);
		this.setSize(1100, 600);
		this.add(buildSplitPane());
		CenterWindow.centerWindow(this);
		this.setVisible(true);
			
	}
//	创建分割面板
	public JSplitPane buildSplitPane() {
		if(splitpane == null) {
			splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			splitpane.setLeftComponent(new JScrollPane(buildLeftPanel()));
			 splitpane.addComponentListener(new ComponentAdapter() {  
            @Override  
            public void componentResized(ComponentEvent e) {  
                splitpane.setDividerLocation(0.45);  
            }  
        });  

			splitpane.setRightComponent(new JScrollPane(buildRightPanel()));
		}
		return splitpane;
	}
	
//	构造左边面板
	public JPanel buildLeftPanel() {
		if(leftpanel == null) {
			leftpanel = new JPanel();
			leftpanel.setLayout(new BorderLayout());
			
			leftpanel.add(fondpanel,BorderLayout.NORTH);
			leftpanel.add(listpanel);
		}
		return leftpanel;
	}
	
//	构造右边面板
	public JPanel buildRightPanel() {
		if(rightpanel == null) {
			rightpanel = new JPanel();
			rightpanel.setLayout(new BorderLayout());
			rightpanel.add(selectpanel,BorderLayout.NORTH);
//			rightpanel.add(classpanel);
			rightpanel.add(notepanel,BorderLayout.SOUTH);
			
		}
		return rightpanel;
	}
	public JTable getProtable() {
		return protable;
	}
	public void setProtable(JTable protable) {
		this.protable = protable;
	}
	
	public JTextField getFondtxt() {
		return fondtxt;
	}
	public void setFondtxt(JTextField fondtxt) {
		this.fondtxt = fondtxt;
	}
	public JTable getListtable() {
		return listtable;
	}
	public void setListtable(JTable listtable) {
		this.listtable = listtable;
	}
	public Vector getListname() {
		return listname;
	}
	public void setListname(Vector listname) {
		this.listname = listname;
	}
    public DefaultTableModel getListmodel() {
        return listmodel;
    }
    public DefaultTableModel getPromodel() {
        return promodel;
    }
    public ProListDao getListdao() {
        return listdao;
    }
    public JTextArea getAdviceArea() {
        return adviceArea;
    }
    public JComboBox getClassbox() {
        return classbox;
    }
    
    

	
	
}
