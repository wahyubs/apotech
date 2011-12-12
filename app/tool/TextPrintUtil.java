package tool;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import play.libs.XPath;
import play.vfs.VirtualFile;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class TextPrintUtil {
	private StringBuffer buffer;
	private int length;

	public TextPrintUtil() {
		buffer = new StringBuffer();
	}

	public void append(String text, int align) throws Exception {
		if (text.length() > length) {
			throw new Exception("Text too big to fit in column!!");
		}
		buffer.append(alignString(text, length, align));
	}

	public void append(String text) throws Exception {
		if (text.length() > length) {
			throw new Exception("Text too big to fit in column!!");
		}
		buffer.append(text);
	}

	public void addLine(int mylength) {
		for (int i = 0; i < mylength; i++) {
			buffer.append("-");
		}
	}

	public void fill(String content, int mylength) {
		for (int i = 0; i < mylength; i++) {
			buffer.append(content);
		}
	}

	public void addNewLine() {
		addNewLine(1);
	}

	public void addNewLine(int times) {
		for (int i = 0; i < times; i++) {
			buffer.append("\n");
		}
	}

	public void addAsciiCode(char ascii) {
		buffer.append(ascii);
	}

	public void beginItalic() {
		addAsciiCode((char) 27);
		addAsciiCode((char) 52);
	}

	public void endItalic() {
		addAsciiCode((char) 27);
		addAsciiCode((char) 53);
	}

	public void beginBold() {
		addAsciiCode((char) 27);
		addAsciiCode((char) 69);
	}

	public void endBold() {
		addAsciiCode((char) 27);
		addAsciiCode((char) 70);
	}

	public void beginUnderline() {
		addAsciiCode((char) 27);
		addAsciiCode((char) 45);
		addAsciiCode((char) 49);
	}

	public void endUnderline() {
		addAsciiCode((char) 27);
		addAsciiCode((char) 45);
		addAsciiCode((char) 48);
	}

	public void beginDoubleHeightFont() {
		addAsciiCode((char) 27);
		addAsciiCode((char) 119);
		addAsciiCode((char) 49);
	}

	public void endDoubleHeightFont() {
		addAsciiCode((char) 27);
		addAsciiCode((char) 119);
		addAsciiCode((char) 48);
		addAsciiCode((char) 20);
	}

	public void setPer180LineSpacing(int n) {
		addAsciiCode((char) 27);
		addAsciiCode((char) 51);
		if (n > 0)
			addAsciiCode((char) n);
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public StringBuffer getBuffer() {
		return buffer;
	}

	public void setBuffer(StringBuffer buffer) {
		this.buffer = buffer;
	}

	public void topHorizontalBorder(int[] size, boolean border) {
		if (border) {
			buffer.append((char) 218);
			for (int i = 0; i < size.length; i++) {
				if (i > 0)
					buffer.append((char) 194);
				for (int j = 0; j < size[i]; j++) {
					buffer.append((char) 196);
				}
			}
			buffer.append((char) 191);
		}
	}

	public void verticalBorder(int[] size, String[] content, int[] align,
			String[] style) {
		verticalBorder(size, content, align, style, true);
	}

	public void verticalBorder(int[] size, String[] content, int[] align,
			String[] style, boolean border) {
		if (border)
			buffer.append((char) 179);
		for (int i = 0; i < size.length; i++) {
			if (i > 0 && border)
				buffer.append((char) 179);
			if (content[i] == null) {
				for (int j = 0; j < size[i]; j++) {
					buffer.append((char) 32);
				}
			} else {
				if (style[i] != null && style[i].length() == 1
						&& style[i].charAt(0) == '1') {
					beginItalic();
				} else if (style[i] != null && style[i].length() == 2) {
					beginBold();
					if (style[i].charAt(1) == '1') {
						beginItalic();
					}
				} else if (style[i] != null && style[i].length() == 3) {
					beginUnderline();
					if (style[i].charAt(1) == '1') {
						beginBold();
					}
					if (style[i].charAt(2) == '1') {
						beginItalic();
					}
				}
				buffer.append(alignString(content[i], size[i], align[i]));
				if (style[i] != null && style[i].length() == 1
						&& style[i].charAt(0) == '1') {
					endItalic();
				} else if (style[i] != null && style[i].length() == 2) {
					endBold();
					if (style[i].charAt(1) == '1') {
						endItalic();
					}
				} else if (style[i] != null && style[i].length() == 3) {
					endUnderline();
					if (style[i].charAt(1) == '1') {
						endBold();
					}
					if (style[i].charAt(2) == '1') {
						endItalic();
					}
				}
			}
		}
		if (border)
			buffer.append((char) 179);
	}

	public void midHorizontalBorder(int[] size, boolean border) {
		if (border) {
			buffer.append((char) 195);
			for (int i = 0; i < size.length; i++) {
				if (i > 0)
					buffer.append((char) 197);
				for (int j = 0; j < size[i]; j++) {
					buffer.append((char) 196);
				}
			}
			buffer.append((char) 180);
		}
	}

	public void bottomHorizontalBorder(int[] size, boolean border) {
		if (border) {
			buffer.append((char) 192);
			for (int i = 0; i < size.length; i++) {
				if (i > 0)
					buffer.append((char) 193);
				for (int j = 0; j < size[i]; j++) {
					buffer.append((char) 196);
				}
			}
			buffer.append((char) 217);
		}
	}

	/**
	 * @param string
	 *            String that will be align
	 * @param size
	 *            The final size of the string
	 * @param align
	 *            choose how to align string
	 * @-1=left align 0=center align 1=right align
	 * @return
	 */
	public static String alignString(String string, int size, int align) {
		String hasil = ""
				+ string.substring(0, string.length() < size ? string.length()
						: size);
		for (int i = string.length(); i < size && string != null; i++) {
			switch (align) {
			case 1:
				hasil = " " + hasil;
				break;
			case -1:
				hasil += " ";
				break;
			case 0:
				int count = i - string.length();
				int half = (size - string.length()) / 2;
				if (count < half) {
					hasil = " " + hasil;
				} else {
					hasil += " ";
				}
				break;
			}
		}
		return hasil;
	}

	@Override
	public String toString() {
		return buffer.toString();
	}

	public void readXml(String bodyXml) {
		try {
			// Reset
			addAsciiCode((char) 27);
			addAsciiCode((char) 64);

			// Setengah Halaman
			addAsciiCode((char) 27);
			addAsciiCode((char) 67);
			addAsciiCode((char) 33);

			// jarak spasi 22/180
			setPer180LineSpacing(22);

			InputStream inputStream = new ByteArrayInputStream(
					bodyXml.getBytes("UTF-8"));
			InputSource inputSource = new InputSource(inputStream);
			DOMParser domParser = new DOMParser();
			domParser.parse(inputSource);
			Document document = domParser.getDocument();
			Node print = XPath.selectNode("print", document);
			String page = XPath.selectText("@page", print);
			String lebar = XPath.selectText("@width", print);
			System.out.println(page);
			System.out.println(lebar);
			length = Integer.parseInt(lebar);
			for (Node tabel : XPath.selectNodes("./table", print)) {
				int barisCount = 0;
				int maxBaris = XPath.selectNodes("./row", tabel).size();
				for (Node baris : XPath.selectNodes("./row", tabel)) {
					String vborder = XPath.selectText("@vborder", baris);
					String hborder = XPath.selectText("@hborder", baris);
					List<Integer> kolomSize = new LinkedList<Integer>();
					List<String> kolomIsi = new LinkedList<String>();
					List<Integer> kolomAlign = new LinkedList<Integer>();
					List<String> kolomStyle = new LinkedList<String>();
					int panjangTotal = 0;
					int maxKolom = "none".equals(vborder) ? 0 : XPath
							.selectNodes("./column", baris).size();
					int kolomCount = 0;
					for (Node kolom : XPath.selectNodes("./column", baris)) {
						String underline = XPath
								.selectText("@underline", kolom);
						String bold = XPath.selectText("@bold", kolom);
						String italic = XPath.selectText("@italic", kolom);
						String bagian = XPath.selectText("@part", kolom);
						String letakHorizontal = XPath.selectText("@align",
								kolom);
						int style = 0;
						if (CommonUtil.isNotEmpty(underline)
								&& Boolean.parseBoolean(underline)) {
							style += 100;
						}
						if (CommonUtil.isNotEmpty(bold)
								&& Boolean.parseBoolean(bold)) {
							style += 10;
						}
						if (CommonUtil.isNotEmpty(italic)
								&& Boolean.parseBoolean(italic)) {
							style += 1;
						}
						kolomStyle.add(String.valueOf(style));
						if ("left".equals(letakHorizontal)) {
							kolomAlign.add(-1);
						} else if ("right".equals(letakHorizontal)) {
							kolomAlign.add(1);
						} else {
							kolomAlign.add(0);
						}
						int panjangKolom = 0;
						if (kolomCount == maxKolom - 1) {
							panjangKolom = length - panjangTotal - maxKolom;
						} else if (CommonUtil.isNotEmpty(bagian)) {
							String[] split = bagian.split("-");
							int pembilang = Integer.parseInt(split[0]);
							int penyebut = Integer.parseInt(split[1]);
							panjangKolom = Math.round(pembilang
									* (length - maxKolom) / penyebut);
						} else {
							panjangKolom = length - maxKolom;
						}
						panjangTotal += panjangKolom;
						kolomSize.add(panjangKolom);
						int size = XPath.selectNodes("./fill", kolom).size();
						if (size == 1) {
							Node selectNode = XPath.selectNode("./fill", kolom);
							String isi = "";
							for (int i = 0; i < panjangKolom; i++) {
								isi += selectNode.getTextContent();
							}
							kolomIsi.add(isi);
						} else {
							kolomIsi.add(kolom.getTextContent());
						}
						kolomCount++;
					}
					int[] size = new int[kolomSize.size()];
					String[] isi = new String[kolomIsi.size()];
					int[] align = new int[kolomAlign.size()];
					String[] style = new String[kolomStyle.size()];
					for (int i = 0; i < kolomSize.size(); i++) {
						size[i] = kolomSize.get(i);
						isi[i] = kolomIsi.get(i);
						align[i] = kolomAlign.get(i);
						style[i] = kolomStyle.get(i);
					}
					if (barisCount == 0) {
						topHorizontalBorder(size,
								"none".equals(hborder) ? false : true);
						addNewLine();
						verticalBorder(size, isi, align, style,
								"none".equals(vborder) ? false : true);
						addNewLine();
						midHorizontalBorder(size,
								"none".equals(hborder) ? false : true);
					} else {
						verticalBorder(size, isi, align, style,
								"none".equals(vborder) ? false : true);
						addNewLine();
						bottomHorizontalBorder(size,
								"none".equals(hborder) ? false : true);
					}
					addNewLine();
					barisCount++;
				}
			}
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Form Feed
		addNewLine();
		addAsciiCode((char) 12);
		// Reset
		addAsciiCode((char) 27);
		addAsciiCode((char) 64);
		System.out.println(buffer);
	}

	public static void main(String[] args) {
		String tes = "" + (char) 18 + (char) 0;
		System.out.println("|" + alignString("tes", 51, 0) + "|");
	}
}
