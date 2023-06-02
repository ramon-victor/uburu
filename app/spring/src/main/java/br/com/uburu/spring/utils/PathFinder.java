package br.com.uburu.spring.utils;

import java.io.File;
import java.util.Optional;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

import br.com.uburu.spring.document.Path;

public final class PathFinder {

    public static Optional<Path> findPath() throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        
        final JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(fc.getFileSystemView().getParentDirectory(new File("C:\\")));  
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            final Path path = new Path();
            path.setPath(fc.getSelectedFile().getAbsolutePath());

            return Optional.of(path);
        }

        return Optional.empty();
    }
    
}
