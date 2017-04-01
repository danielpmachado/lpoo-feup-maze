package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.MatteBorder;

public class DefeatPanel extends JPanel {

    /**
     * Create the panel.
     */
    public DefeatPanel() {

        setBackground(Color.BLACK);
        ImageIcon defeat = new ImageIcon(this.getClass().getResource("res/thumbsDownCut.png"));
        JLabel label = new JLabel(defeat);
        label.setBounds(10, 49, 512, 400);
        
        setMenuButton();
        setExitButton();
        add(label);
       
    
        
    }
    
    
    public void setMenuButton(){
    	
    	JButton menuBtn = new JButton("");
    	menuBtn.setBounds(106, 486, 122, 45);
        menuBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameWindow.pnlDefeat.setVisible(false);
                GameWindow.pnlMenu.setVisible(true);
            }
        });
        setLayout(null);
        menuBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuBtn.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        menuBtn.setIcon(new ImageIcon(VictoryPanel.class.getResource("/gui/res/rsz_menu.png")));
        add(menuBtn);
    }
    
    public void setExitButton(){
    	
    	JButton exitBtn = new JButton("");
    	exitBtn.setBounds(310, 486, 103, 45);
        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        exitBtn.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        exitBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        exitBtn.setIcon(new ImageIcon(VictoryPanel.class.getResource("/gui/res/rsz_exit.png")));
        add(exitBtn);
    }

}



