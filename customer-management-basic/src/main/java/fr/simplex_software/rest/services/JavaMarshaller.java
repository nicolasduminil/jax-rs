package fr.simplex_software.rest.services;

import java.io.*;
import java.lang.annotation.*;
import java.lang.reflect.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.*;

@SuppressWarnings("rawtypes")
@Provider
@Produces("application/x-java-serialized-object")
@Consumes("application/x-java-serialized-object")
public class JavaMarshaller implements MessageBodyReader, MessageBodyWriter
{
  public boolean isReadable(Class type, Type genericType, Annotation[] annotations, MediaType mediaType)
  {
    return Serializable.class.isAssignableFrom(type);
  }

  public Object readFrom(Class type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap httpHeaders, InputStream is) throws IOException, WebApplicationException
  {
    ObjectInputStream ois = new ObjectInputStream(is);
    try
    {
      return ois.readObject();
    }
    catch (ClassNotFoundException e)
    {
      throw new RuntimeException(e);
    }
  }

  public boolean isWriteable(Class type, Type genericType, Annotation[] annotations, MediaType mediaType)
  {
    return Serializable.class.isAssignableFrom(type);
  }

  public long getSize(Object o, Class type, Type genericType, Annotation[] annotations, MediaType mediaType)
  {
    return -1;
  }

  public void writeTo(Object o, Class type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap httpHeaders, OutputStream os) throws IOException, WebApplicationException
  {
    ObjectOutputStream oos = new ObjectOutputStream(os);
    oos.writeObject(o);
  }
}