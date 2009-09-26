/*
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.shrinkwrap.impl.base.spec;

import org.jboss.shrinkwrap.api.Path;
import org.jboss.shrinkwrap.api.container.ClassContainer;
import org.jboss.shrinkwrap.api.container.LibraryContainer;
import org.jboss.shrinkwrap.api.container.ManifestContainer;
import org.jboss.shrinkwrap.api.container.ResourceContainer;
import org.jboss.shrinkwrap.api.spec.ResourceAdapterArchive;
import org.jboss.shrinkwrap.impl.base.MemoryMapArchiveImpl;
import org.jboss.shrinkwrap.impl.base.path.BasicPath;
import org.jboss.shrinkwrap.impl.base.test.ContainerTestBase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * ResourceAdapterArchiveImplTestCase
 *
 * Test to ensure that ResourceAdapterArchiveImpl follow to java rar spec.
 *
 * @author <a href="mailto:baileyje@gmail.com">John Bailey</a>
 * @version $Revision: $
 */
public class ResourceAdapterArchiveImplTestCase extends ContainerTestBase<ResourceAdapterArchive>
{
   //-------------------------------------------------------------------------------------||
   // Class Members ----------------------------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   private static final String TEST_RESOURCE = "org/jboss/shrinkwrap/impl/base/asset/Test.properties";

   private static final Path PATH_RESOURCE = new BasicPath("/");

   private static final Path PATH_MANIFEST = new BasicPath("META-INF");

   private static final Path PATH_LIBRARY = new BasicPath("/");

   //-------------------------------------------------------------------------------------||
   // Instance Members -------------------------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   private ResourceAdapterArchive archive;

   //-------------------------------------------------------------------------------------||
   // Lifecycle Methods ------------------------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   @Before
   public void createResourceAdapterArchive() throws Exception
   {
      archive = createNewArchive();
   }

   @After
   public void ls()
   {
      System.out.println("test@jboss:/$ ls -l " + archive.getName());
      System.out.println(archive.toString(true));
   }

   //-------------------------------------------------------------------------------------||
   // Required Impls - ArchiveTestBase ---------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   /** 
    * Return the current ResourceAdapterArchive
    */
   @Override
   protected ResourceAdapterArchive getArchive()
   {
      return archive;
   }

   /**
    * Create a new instance of a ResourceAdapterArchive
    */
   @Override
   protected ResourceAdapterArchive createNewArchive()
   {
      return new ResourceAdapterArchiveImpl(new MemoryMapArchiveImpl());
   }

   //-------------------------------------------------------------------------------------||
   // Required Impls - ContainerTestBase -------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   @Override
   protected ClassContainer<ResourceAdapterArchive> getClassContainer()
   {
      throw new UnsupportedOperationException("ResourceAdapterArchive do not support classes");
   }

   @Override
   protected Path getClassesPath()
   {
      throw new UnsupportedOperationException("ResourceAdapterArchive do not support classes");
   }

   @Override
   protected LibraryContainer<ResourceAdapterArchive> getLibraryContainer()
   {
      return archive;
   }

   @Override
   protected Path getManifestPath()
   {
      return PATH_MANIFEST;
   }

   @Override
   protected Path getResourcePath()
   {
      return PATH_RESOURCE;
   }

   @Override
   protected Path getLibraryPath()
   {
      return PATH_LIBRARY;
   }

   @Override
   protected ManifestContainer<ResourceAdapterArchive> getManifestContainer()
   {
      return getArchive();
   }

   @Override
   protected ResourceContainer<ResourceAdapterArchive> getResourceContainer()
   {
      return getArchive();
   }

   //-------------------------------------------------------------------------------------||
   // Tests ------------------------------------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   @Test
   public void testAddResourceAdapterXML() throws Exception
   {
      archive.setResourceAdapterXML(TEST_RESOURCE);

      Path expectedPath = new BasicPath(PATH_MANIFEST, "ra.xml");

      Assert.assertTrue("ra.xml should be located in /META-INF/ra.xml", archive.contains(expectedPath));
   }

   @Test
   public void testAddResourceAdapterXMLRequireResource() throws Exception
   {
      try
      {
         archive.setResourceAdapterXML(null);
         Assert.fail("Should have thrown IllegalArgumentException");
      }
      catch (IllegalArgumentException expected)
      {
      }

   }

   @Ignore
   @Override
   public void testAddClass() throws Exception
   {
   }

   @Ignore
   @Override
   public void testAddClasses() throws Exception
   {
   }

   @Ignore
   @Override
   public void testAddPackage() throws Exception
   {
   }

   @Ignore
   @Override
   public void testAddPackageNonRecursive() throws Exception
   {
   }

}
