package util;

import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class Tabela extends JTable{

	private DefaultTableModel model;
	private List<String> lista;
	
	public Tabela(){
		iniciarModel(new String[]{"Posicao", "Tempo"});
		this.setModel(model);
		configurar();
	}
	
	private void configurar(){
		
		this.getTableHeader().setReorderingAllowed(false);
		this.setCellSelectionEnabled(false);
		this.setRowSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	private void iniciarModel(String [] titulos){
		
		model = new DefaultTableModel(new Object [][] { }, titulos){    
			
			         boolean[] canEdit = new boolean []{      
			             false, false  
			         };      
			        
			         @Override      
			         public boolean isCellEditable(int rowIndex, int columnIndex) {      
			             return canEdit [columnIndex];      
			         }    
			         
			         public Class getColumnClass(int column) {
			        	 return Object.class;
			         }
		};    
	}
	
	public void montarTabela(List<String> lista){
		limparTabela();
		this.lista = lista;
		for(int i = 0; i < lista.size(); i++){
			String t = lista.get(i);
			model.insertRow(i, new Object [] {i+1, t});
		}
	}
	
	public void limparTabela(){
		while(model.getRowCount() > 0){
			model.removeRow(0);
		}
	}
}
