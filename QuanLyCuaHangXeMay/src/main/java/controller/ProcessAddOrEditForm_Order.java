package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import model.DBMotorcycleSerie;
import model.DBOrder;
import view.OrderTable;
import view.OutstockTable;

public class ProcessAddOrEditForm_Order implements ActionListener {

	private JDialog _AddOrEditForm_Order;
	private ArrayList<Integer> _buffer;
	
	public ProcessAddOrEditForm_Order(JDialog AddOrEditForm_Order) {
		_AddOrEditForm_Order = AddOrEditForm_Order;
		_buffer = new ArrayList<Integer>();
	}

	public void actionPerformed(ActionEvent e) {
		int index = -1;
		_buffer.clear();
		
		if(!((AddOrEditForm_Order)_AddOrEditForm_Order).isAdd()) {
			index = ClickedRowService_Order.currentIndex();
			if (-1 == index) {
				return;
			}
		}
		
		--index;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date = format.parse(((AddOrEditForm_Order)_AddOrEditForm_Order).dateField().getText());
			for(int i = 0; i < AddOrEditForm_Order.table().getModel().getRowCount(); ++i) {
				Integer quantityInStoraged = (Integer) AddOrEditForm_Order.table().getModel().getValueAt(i, 3);
				Integer quantityBuy = (Integer) AddOrEditForm_Order.table().getModel().getValueAt(i, 4);
				
				if (null != quantityBuy && quantityBuy > quantityInStoraged) {
					JOptionPane.showMessageDialog(null,
						    "Số lượng mua không được lớn hơn số lượng còn trong kho",
						    "Lỗi số lượng",
						    JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (null != quantityBuy && quantityBuy < 0) {
					JOptionPane.showMessageDialog(null,
						    "Số lượng mua không được âm",
						    "Lỗi số lượng",
						    JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				_buffer.add(quantityBuy);
			}
			
			
			//Add process
			if (((AddOrEditForm_Order)_AddOrEditForm_Order).isAdd()) {
				DBOrder.addOrder(date, _buffer);
				OrderTable.readData(DBOrder.currentOrders());
				OutstockTable.readData(DBMotorcycleSerie.currentSeries());
			} else {
				//Edit process
				DBOrder.editOrder(date, _buffer);
				OrderTable.readData(DBOrder.currentOrders());
				OutstockTable.readData(DBMotorcycleSerie.currentSeries());
			}
			
			//Repaint the table
			OrderTable.table().repaint();
			
			//End Frame
			_AddOrEditForm_Order.dispose();
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null,
				    "Kiểm tra lại ô Ngày đặt hàng: ngày tháng bắt buộc phải có, yêu cầu đúng format dd/mm/yyyy",
				    "Lỗi ngày tháng",
				    JOptionPane.ERROR_MESSAGE);
			return;
			
		}
		
	}
}
