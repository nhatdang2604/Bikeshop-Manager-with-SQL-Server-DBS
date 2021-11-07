package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.DBMotorcycleSerie;
import model.DBOrder;
import view.MotorcycleSeriesTable;
import view.OrderTable;

public class DeleteService_Order implements ActionListener{
	private void _deleteProcess(int index) {
		DBOrder.deleteOrder();
		OrderTable.readData(DBOrder.currentOrders());
		OrderTable.detailInformationPanel().setDataToDetailPanel(-1);
	}
	
	public DeleteService_Order() {
		//do nothing
	}
	
	public void actionPerformed(ActionEvent arg0) {
        int click = JOptionPane.showConfirmDialog(null,
        		"Bạn có chắc chắn muốn xóa ?\nDữ liệu bị xóa sẽ không thể khôi phục lại được.",
        		"Xóa",
        		JOptionPane.YES_NO_OPTION);
        
        if (JOptionPane.YES_OPTION == click) {
            int index = -1;
        	
        	
        	index = ClickedRowService_Order.currentIndex();
			if (-1 == index) {
				return;
			}
			--index;
			_deleteProcess(index);
		
			OrderTable.table().repaint();
			
			JOptionPane.showMessageDialog(null, "Đã xóa thành công.");
		}
       
    }
}
