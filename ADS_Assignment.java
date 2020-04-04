/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads_assignment;

/**
 *
 * @author MY PC
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class ADS_Assignment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {

        String ptrnLine, textLine,inpLine,sFilePath,srcLine;
	String input;
	int srcLineIndex=1, inpLineIndex=1;
        KMP kmpComponent;
	BoyerMoore bmComponent;
        int inputLen,srcLen,patterntextLength;
	double kmpSimRatio = 0, boyreMooreSimRatio = 0;
        int fullTextLength=0, fullPatternLength=0;
        int count=0;
	int mainTimeCount= 0,kmpComponentCount=0, bmComponentCount=0;
        final File folder = new File("D:\\Data_Source");
	File fileKmp = new File("kmp.txt"); // kmp result to be written in kmp.txt

	FileWriter outKmpFile = new FileWriter(fileKmp, true);
        for (final File fileEntry : folder.listFiles())
        {
            sFilePath = fileEntry.getPath();
	    srcLineIndex=1;
            File sourceFile = new File(sFilePath);
            File inputFile = new File( "input.txt"); // the source file to be examined for plagiarism
			
            BufferedReader sReader = new BufferedReader( new FileReader(sourceFile));
            while((srcLine = sReader.readLine())!=null)
            {
		BufferedReader reader = new BufferedReader( new FileReader(inputFile));
		inpLineIndex=1;
                fullTextLength = fullTextLength+srcLine.length();
		while((inpLine = reader.readLine())!=null)
                {
                    inputLen = inpLine.length();
                    srcLen = srcLine.length();
				
                    if(inputLen>0 && srcLen>0) 
                    {
			if(srcLen>inputLen)
			{
                            textLine = srcLine;
                            ptrnLine = inpLine;
			}
			else
			{
                            textLine = inpLine;
                            ptrnLine = srcLine;
			}
		        patterntextLength = ptrnLine.length();

			if(count<1)
			{
                            fullPatternLength = fullPatternLength+ ptrnLine.length();
			}
			kmpComponent = new KMP();
			if(patterntextLength!=0)
			{ 
                            kmpSimRatio= (kmpComponent.searchSubString(textLine, ptrnLine)/(double)(patterntextLength));
			}
			System.out.println("KMP Result");
			System.out.println("Similarity ratio = "+kmpSimRatio*100.000+" Line Number of the input file= "+inpLineIndex+
			   " Line Number of the source file "+fileEntry.getName()+ " = "+srcLineIndex);
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
			PrintWriter outPKmpFile = new PrintWriter(outKmpFile);
                        if(kmpSimRatio>0.010)
                        { 
                           outPKmpFile.append("Line "+inpLineIndex + " of the input file has plagarised " +kmpSimRatio*100.000+"% from line "+srcLineIndex +" of the source file "+fileEntry.getName()+"\n");
                        }

                        //Computing KMP Time Count
                        kmpComponentCount = kmpComponentCount+kmpComponent.getTimeCount();
                        System.out.println("The amount of time KMP took for a text lenghth "+textLine.length()+" and pattern length " +ptrnLine.length()+" is "+
                            kmpComponentCount);
                        mainTimeCount = kmpComponentCount+mainTimeCount;
                    }
                }
            }
        }
    }
}

                                

