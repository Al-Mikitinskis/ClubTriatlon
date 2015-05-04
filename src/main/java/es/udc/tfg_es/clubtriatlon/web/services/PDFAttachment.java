package es.udc.tfg_es.clubtriatlon.web.services;

/* ClubTriatlon: a web app to management of administrative work of a triathlon club
 Copyright (C) 2015 Alejandro Mikitinskis

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software Foundation,
 Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA

 Contact here: alejandro.mikitinskis@udc.es */

import java.io.IOException;
import java.io.InputStream;

import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.services.Response;

public class PDFAttachment implements StreamResponse {
	
	private InputStream	is			= null;
	protected String	contentType	= "application/pdf";
	protected String	extension	= "pdf";
	protected String	filename	= "document";
	
	public PDFAttachment(InputStream is, String filenameIn) {
		this.is = is;
		if (filenameIn != null) {
			this.filename = filenameIn;
		}
	}
	
	public PDFAttachment(InputStream is) {
		this.is = is;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public InputStream getStream() throws IOException {
		return is;
	}
	
	public void prepareResponse(Response arg0) {
		//filename is already finish with the extension (e.g. myDocument.pdf)
		arg0.setHeader("Content-Disposition", "attachment; filename=" + filename);
//		arg0.setHeader("Content-Disposition", "attachment; filename=" + filename
//				+ ((extension == null) ? "" : ("." + extension)));
		arg0.setHeader("Expires", "0");
		arg0.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		arg0.setHeader("Pragma", "public");
		// We can't set the length here because we only have an Input Stream at
		// this point. (Although we'd like to.)
		// We can only get size from an output stream.
		// arg0.setContentLength(.length);
	}
}
