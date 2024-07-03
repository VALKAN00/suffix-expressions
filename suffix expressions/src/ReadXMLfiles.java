import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;
import java.io.IOException;

import java.util.ArrayList;

public class ReadXMLfiles {

    static ArrayList <String>A1=new ArrayList<>();


    public static void main (String[] args) throws ParserConfigurationException, IOException {
        MyStack s1=new MyStack();
        int num_counter=0;
        int op_counter=0;
        MyStack s2=new MyStack();
        String x="";
        String y="";
        String z="";
        String sum="";
        double a=0;
        double b=0;
        double  sum2 =0;
        try {
            // Load and parse the XML file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("prefix.xml");

            // Get the root element
            Element root = document.getDocumentElement();

            // Process the root element and print the result
            String result = readxml(root);

            //handle invalid prefix form
            for (int i=0;i< A1.size()-1;i++) {
                if(A1.get(i).equals("+") || A1.get(i).equals("-") || A1.get(i).equals("/") ||A1.get(i).equals("*") ){
                    op_counter++;
                }
                num_counter= A1.size()-op_counter;
            }
            if (num_counter-op_counter!=1) {
                System.out.println("invalid prefix format");
                System.exit(0);
            }







            //Start evaluation from here ********************

            for (int i=A1.size()-1;i>=0;i--)
            { if (A1.get(i).equals("+")){
                x = s2.pop();
                y = A1.get(i);
                z = s2.pop();
                sum = "("+x+y+z+")";
                s2.push(sum);

            }   else if (A1.get(i).equals("-")){
                x = s2.pop();
                y = A1.get(i);
                z = s2.pop();
                sum ="("+x+y+z+")";
                s2.push(sum);


            }

            else if (A1.get(i).equals("/")){
                x = s2.pop();
                y = A1.get(i);
                z = s2.pop();
                sum = "("+x+y+z+")";
                s2.push(sum);

            }   else  if (A1.get(i).equals("*")){
                x = s2.pop();
                y = A1.get(i);
                z = s2.pop();
                sum = "("+x+y+z+")";
                s2.push(sum);

            }    else {
                s2.push(A1.get(i));
            }

            }





            for (int i=A1.size()-1;i>=0;i--)
            { if (A1.get(i).equals("+")){
                a = Double.parseDouble(s1.pop());
                b = Double.parseDouble(s1.pop());
                sum2=a+b;
                s1.push(String.valueOf(sum2));

            }   else if (A1.get(i).equals("-")){
                a = Double.parseDouble(s1.pop());
                b = Double.parseDouble(s1.pop());
                sum2 = a-b;
                s1.push(String.valueOf(sum2));


            }

            else if (A1.get(i).equals("/")){
                a = Double.parseDouble(s1.pop());
                b = Double.parseDouble(s1.pop());
                sum2 = a/b;
                s1.push(String.valueOf(sum2));

            }   else  if (A1.get(i).equals("*")){
                a = Double.parseDouble(s1.pop());
                b = Double.parseDouble(s1.pop());
                sum2 = a*b;
                s1.push(String.valueOf(sum2));

            }   else {
                s1.push(A1.get(i));
            }

            }

            System.out.println("Expression =  "+ s2.pop());
            System.out.println("resuilt =  " + s1.pop());

        } catch (SAXException e){
            System.out.println("invaild xml file");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("invalid path ");
            System.exit(0);
        }


    }

    private static String readxml(Element element) {
        // Process child elements
        NodeList childNodes = element.getChildNodes();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i) instanceof Element childElement) {

                // Check for "operator" element
                if ("operator".equals(childElement.getTagName())) {
                    if (childElement.getAttribute("value").equals("+") || childElement.getAttribute("value").equals("-") || childElement.getAttribute("value").equals("*") || childElement.getAttribute("value").equals("/")) {
                        A1.add(childElement.getAttribute("value"));
                        result.append(childElement.getAttribute("value") + " ");

                    } else {
                        //when operator contain numbers
                        System.out.println("invalid prefix format in xml");
                        System.exit(0);
                    }

                }

                // Check for "atom" element
                if ("atom".equals(childElement.getTagName())) {
                    if (!childElement.getAttribute("value").equals("+") && !childElement.getAttribute("value").equals("-") && !childElement.getAttribute("value").equals("*") && !childElement.getAttribute("value").equals("/")) {
                        A1.add(childElement.getAttribute("value"));
                        result.append(childElement.getAttribute("value") + " ");
                    } else {
                        //when atom contain operator
                        System.out.println("invalid prefix format in xml");
                        System.exit(0);
                    }

                }

                // Recursively process child elements and append to the result
                result.append(readxml(childElement));
            }
        }
        return result.toString();
    }

}
