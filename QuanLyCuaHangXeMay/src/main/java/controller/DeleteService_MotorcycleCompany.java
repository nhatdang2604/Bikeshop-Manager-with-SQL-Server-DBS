package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.DBMotorcycleCompany;
import model.DBMotorcycleSerie;
import model.DBOrder;
import view.MotorcycleCompanyTable;
import view.MotorcycleSeriesTable;
import view.OrderTable;

public class DeleteService_MotorcycleCompany implements ActionListener {
	
	private void _deleteProcess(int index) {
		DBMotorcycleCompany.deleteMotorcycleCompany(index);
		MotorcycleCompanyTable.readData(DBMotorcycleCompany.currentCompanies());
		MotorcycleCompanyTable.detailInformationPanel().setDataToDetailPanel(-1);
		MotorcycleSeriesTable.detailInformationPanel().setDataToDetailPanel(-1);
		
		DBMotorcycleSerie.updateSeriesBuffer();
		MotorcycleSeriesTable.readData(DBMotorcycleSerie.currentSeries());
		AddOrEditForm_MotorcycleSerie.updateCompanies();
		DBOrder.updateOrderBuffer();
		OrderTable.readData(DBOrder.currentOrders());
		
		
	}
	
	public DeleteService_MotorcycleCompany() {
		//do nothing
	}
	
	public void actionPerformed(ActionEvent arg0) {
        int click = JOptionPane.showConfirmDialog(null,
        		"Bạn có chắc chắn muốn xóa ?\nDữ liệu bị xóa sẽ không thể khôi phục lại được.",
        		"Xóa",
        		JOptionPane.YES_NO_OPTION);
        
        if (JOptionPane.YES_OPTION == click) {
            int index = -1;
        	
        	
        	index = ClickedRowService_MotorcycleCompany.currentIndex();
			if (-1 == index) {
				return;
			}
			--index;
			_deleteProcess(index);
		
			MotorcycleCompanyTable.table().repaint();
			
			JOptionPane.showMessageDialog(null, "Đã xóa thành công.");
		}
       
    }
}	
