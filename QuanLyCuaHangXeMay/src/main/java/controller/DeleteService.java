package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class DeleteService implements ActionListener {
	
	public DeleteService() {
		//does nothing yet
	}
	
	public void actionPerformed(ActionEvent arg0) {
        int click = JOptionPane.showConfirmDialog(null,
        		"Bạn có chắc chắn muốn xóa ?\nDữ liệu bị xóa sẽ không thể khôi phục lại được.",
        		"Xóa",
        		JOptionPane.YES_NO_OPTION);
        
        if (JOptionPane.YES_OPTION == click) {
             JOptionPane.showMessageDialog(null, "Đã xóa thành công.");
             
             //To do
             
        }  
       
    }
}
