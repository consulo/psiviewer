/*
    IDEA PsiViewer Plugin
    Copyright (C) 2002 Andrew J. Armstrong

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

	Author:
	Andrew J. Armstrong <andrew_armstrong@bigpond.com>
*/

package idea.plugin.psiviewer.view;

import com.intellij.psi.*;
import com.intellij.psi.xml.*;
import idea.plugin.psiviewer.PsiViewerConstants;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

class PsiViewerTreeCellRenderer extends DefaultTreeCellRenderer implements PsiViewerConstants
{
    private final ElementVisitor _elementVisitor;

    public PsiViewerTreeCellRenderer()
    {
        setOpaque(false);
        _elementVisitor = new ElementVisitor();
    }

    public Component getTreeCellRendererComponent(JTree tree,
                                                  Object value,
                                                  boolean isSelected,
                                                  boolean isExpanded,
                                                  boolean isLeaf,
                                                  int row,
                                                  boolean hasFocus)
    {
        super.getTreeCellRendererComponent(tree, value, isSelected, isExpanded, isLeaf, row, hasFocus);

        setIcon(IconCache.DEFAULT_ICON);

        PsiElement psiElement = (PsiElement) value;
        psiElement.accept(_elementVisitor);

        return this;
    }

    private class ElementVisitor extends PsiElementVisitor
    {
        private static final int MAX_TEXT_LENGTH = 80;

        public void visitReferenceExpression(PsiReferenceExpression psiReferenceExpression)
        {
        }

        public void visitBinaryFile(PsiBinaryFile psiElement)
        {
            setIcon(IconCache.getIcon(PsiBinaryFile.class));
            setText("PsiBinaryFile: " + psiElement.getName());
        }

        public void visitClass(PsiClass psiElement)
        {
            setIcon(IconCache.getIcon(PsiClass.class));
            setText("PsiClass: " + psiElement.getName());
        }

        public void visitComment(PsiComment psiElement)
        {
            setIcon(IconCache.getIcon(PsiComment.class));
            setText("PsiComment: " + truncate(psiElement.getText()));
        }

        public void visitDirectory(PsiDirectory psiElement)
        {
            setIcon(IconCache.getIcon(PsiDirectory.class));
            setText("PsiDirectory: " + psiElement.getName());
        }

        public void visitElement(PsiElement psiElement)
        {
            setText(psiElement.toString());
        }

        public void visitField(PsiField psiElement)
        {
            setIcon(IconCache.getIcon(PsiField.class));
            setText("PsiField: " + psiElement.getName());
        }

        public void visitFile(PsiFile psiElement)
        {
            setText("PsiFile: " + psiElement.getName());
        }

        public void visitJavaFile(PsiJavaFile psiElement)
        {
            setIcon(IconCache.getIcon(PsiJavaFile.class));
            setText("PsiJavaFile: " + psiElement.getName());
        }

        public void visitJavaToken(PsiJavaToken psiElement)
        {
            setText("PsiJavaToken: " + psiElement.getText());
        }

        public void visitMethod(PsiMethod psiElement)
        {
            setIcon(IconCache.getIcon(PsiMethod.class));
            setText("PsiMethod: " + psiElement.getName());
        }

        public void visitPlainTextFile(PsiPlainTextFile psiElement)
        {
            setIcon(IconCache.getIcon(PsiPlainTextFile.class));
            setText("PsiPlainTextFile: " + psiElement.getName());
        }

        public void visitVariable(PsiVariable psiElement)
        {
            setIcon(IconCache.getIcon(PsiVariable.class));
            setText("PsiVariable: " + psiElement.getName());
        }

        public void visitWhiteSpace(PsiWhiteSpace psiElement)
        {
            setIcon(IconCache.getIcon(PsiWhiteSpace.class));
            setText("PsiWhiteSpace");
        }

        public void visitXmlAttribute(XmlAttribute psiElement)
        {
            setIcon(IconCache.getIcon(XmlAttribute.class));
            setText("XmlAttribute: " + psiElement.getName());
        }

        public void visitXmlAttributeValue(XmlAttributeValue psiElement)
        {
            setText("XmlAttributeValue");
        }

        public void visitXmlComment(XmlComment psiElement)
        {
            setIcon(IconCache.getIcon(XmlComment.class));
            setText("XmlComment");
        }

        public void visitXmlDecl(XmlDecl psiElement)
        {
            setText("XmlDecl");
        }

        public void visitXmlDoctype(XmlDoctype psiElement)
        {
            setText("XmlDoctype");
        }

        public void visitXmlDocument(XmlDocument psiElement)
        {
            setText("XmlDocument");
        }

        public void acceptXmlFile(XmlFile psiElement)
        {
            setIcon(IconCache.getIcon(XmlFile.class));
            setText("XmlFile: " + psiElement.getName());
        }

        public void visitXmlProlog(XmlProlog psiElement)
        {
            setText("XmlProlog");
        }

        public void visitXmlTag(XmlTag psiElement)
        {
            setIcon(IconCache.getIcon(XmlTag.class));
            setText("XmlTag: " + psiElement.getName());
        }

        public void visitXmlToken(XmlToken psiElement)
        {
            setText("XmlToken: " + psiElement.getText());
        }

        private String truncate(String text)
        {
            if (text.length() > MAX_TEXT_LENGTH)
                return text.substring(0, MAX_TEXT_LENGTH).trim() + "...";
            else
                return text;
        }
    }

}