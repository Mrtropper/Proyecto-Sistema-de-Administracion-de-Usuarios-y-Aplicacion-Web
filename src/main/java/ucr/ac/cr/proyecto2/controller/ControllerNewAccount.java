/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package ucr.ac.cr.proyecto2.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import ucr.ac.cr.proyecto2.model.TbUsuarios;
import ucr.ac.cr.proyecto2.view.GUINewAccount;

/**
 *
 * @author Usuario
 */
public class ControllerNewAccount implements ActionListener, ItemListener {

    GUINewAccount newAccount;
    TbUsuariosJpaController jpaUsuarios;
    TbUsuarios tbUsuarios;
    
    public ControllerNewAccount(){
        newAccount = new GUINewAccount();
        jpaUsuarios = new TbUsuariosJpaController();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Create account":
                if(newAccount.getSelectrbUser()){
                    
                    if(validation()){

                        try {
                            TbUsuarios userTb = new TbUsuarios(newAccount.getTxtUsername(),
                                    newAccount.getTxtUsername(),
                                    newAccount.getTxtName(), "User");
                            
                            
                            
                            
                            
                            jpaUsuarios.add(userTb);
                            newAccount.dispose();
                        } catch (Exception ex) {
                            Logger.getLogger(ControllerNewAccount.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                }else if(newAccount.getSelectrbAdmin()){
                    
                    if(validation()){
                        
                        if(newAccount.txtSpCodeIsEmpty() == false){
                            
                            if("if3000".equals(newAccount.getTxtSpCode())){

                                try {
                                    TbUsuarios user = new TbUsuarios(newAccount.getTxtUsername(),
                                            newAccount.getTxtUsername(),
                                            newAccount.getTxtName(), "Admin");
                                    
                                    
                                    
                                    jpaUsuarios.add(user);
                                    newAccount.dispose();
                                } catch (Exception ex) {
                                    Logger.getLogger(ControllerNewAccount.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                
                                
                            }else{
                                newAccount.getMessage("invalid admin code");
                            }
                            
                        }else{
                            newAccount.getMessage("You must fill in all the spaces");
                        }    
                        
                    }
                
                }//fin del if para el caso en el que sea un usuario
                
                break;
        }
    }
    public boolean validation(){
        if(newAccount.txtIsEmptyUser()){
            newAccount.getMessage("You must fill in all the spaces");
            return false;
        }
        return true;
    }//Fin del metodo

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource().toString().equals(newAccount.getRbUser())){
            newAccount.setTxtCodeEnabled(false);
            
        }
        
        if(e.getSource().toString().equals(newAccount.getRbAdmin())){
            
            newAccount.setTxtCodeEnabled(true);
            
            
        }
    }
}