/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseproj;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class WebSeries extends DbObject {
	
	private String sName;
	protected int sNameLen = 30;
	
	private String ottPlat;
	protected int ottPlatLen = 30;
	
	private int seasons;
	protected int seasonsLen=4;
	
	private String genre;
	protected int genreLen=30;
	
	private String director;
	protected int directorLen=30;
	
	protected int size = 2*sNameLen+2*ottPlatLen+seasonsLen + 2*genreLen+2*directorLen;
	//the length of string is counted twice because of writing into file as array
	
	public WebSeries() {	
	}

	public WebSeries(String s1, String s2, int i, String s3, String s4) {
		sName=s1;
		ottPlat=s2;
		seasons=i;
		genre=s3;
		director=s4;
	}
	
	public boolean equals(Object d) {
		
		return sName.trim().equals(((WebSeries) d).sName.trim());
	}
	@Override
	public void writeToFile(RandomAccessFile out) throws IOException {
		writeString(sName, out);
		writeString(ottPlat, out);
		out.writeInt(seasons);
		writeString(genre,out);
		writeString(director,out);
		
	}

	@Override
	public void readFromFile(RandomAccessFile in) throws IOException {
		sName = readString(sNameLen, in);
		ottPlat = readString(ottPlatLen, in);
		seasons = in.readInt();
		genre=readString(genreLen, in);
		director = readString(directorLen, in);

	}

	@Override
	public void readFromConsole() {
		Scanner kb= new Scanner(System.in);
		
		System.out.print("Enter Series Name: ");

                sName = kb.nextLine();
		
		for(int i = sName.length(); i< sNameLen; i++)
			sName+=' ';

		System.out.print("Enter OTT Platform: ");
		ottPlat= kb.next();
		for(int i = ottPlat.length(); i< ottPlatLen; i++)
			ottPlat+=' ';
		
		System.out.print("Number of Seasons: ");
		seasons = kb.nextInt();
		
		//System.out.print("Here is error: " + kb.next());
		
		System.out.print("Genre: ");
		genre=kb.next();

		for(int i = genre.length(); i< genreLen; i++)
			genre+=' ';
		
		System.out.print("Name of Director: ");
		director=kb.next();

		for(int i = director.length(); i< directorLen; i++)
			director+=' ';
		
		
		}
		
	

	@Override
	public void writeLegibly() {
		System.out.print("The webseries " + sName.trim() + " directed by " + director.trim() + " has " + seasons + " seasons. It belongs to " + genre.trim() + " genre and is released on "+ ottPlat.trim()+".");
	}

	@Override
	public void readKey() {
		System.out.println("Enter Webseries Name: ");
		Scanner kb= new Scanner(System.in);

                sName = kb.nextLine();
                System.out.println("Comparing Name: " + sName);
	}

	@Override
	public void copy(DbObject[] db) {
		db[0] = new WebSeries(sName, ottPlat, seasons, genre, director);

	}

	@Override
	public int size() {
		
		return size;
	}

}