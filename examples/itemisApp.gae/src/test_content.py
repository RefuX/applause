'''
Created on Mar 19, 2011

@author: behrens
'''
import unittest
import providers as content

import lib.custom_filters

l = lib.custom_filters.to_list

class Test(unittest.TestCase):
    
    def testList(self):
        self.assertEqual(1, len(l("foo")))
        self.assertEqual(1, len(["foo"]))
        self.assertEqual(3, len("foo"))

    def testAllSpeakers(self):
        self.assertEqual(207, len(content.allSpeakers()))
        
    def testContainsHTML(self):
        speaker= content.speakerByName("Peter Friese")
        bio = str(speaker.bio)
        self.assertTrue("</a>" in bio)
        
    def testSpeakerById(self):
        speaker = content.speakerById("eclipse")
        self.assertEqual("Eclipse", str(speaker.name))
        self.assertEqual("eclipse", str(speaker.id))
        self.assertEqual("/media/files/speakers/eclipse.png", str(speaker.pictureurl))
        self.assertEqual(41, len(l(speaker.sessions)))
        
    def testSpeakerByName(self):
        speaker = content.speakerByName("Heiko Behrens")
        
        self.assertEqual("Heiko Behrens", str(speaker.name))
        self.assertEqual("hbehrens", str(speaker.id))
        self.assertEqual("/media/files/speakers/hbehrens.png", str(speaker.pictureurl))
        
        self.assertEqual(1, len(l(speaker.sessions)))
        session = l(speaker.sessions)[0]
        self.assertEqual("2075", str(session.id))
        
    def testSessionsByDay(self):
        sessions = content.sessionsByDay('2011-03-21')
        self.assertEqual(52, len(l(sessions)))
        first = l(sessions)[0]
        self.assertEqual("Making Community", str(first.category));
        
    def testSessionById(self):
        session = content.sessionById("2075")
        self.assertEqual("Cross-Platform Mobile Development with Eclipse", str(session.title))
        self.assertEqual(2, len(l(session.speakers)))
        
    def testAllBlogItems(self):
        items = content.allBlogItems()
        self.assertEqual(30, len(l(items)))
        
        item = l(items)[0]
        self.assertEqual("http://heikobehrens.net/2011/03/18/what-is-eclipsecon/", str(item.link))
        
    def testXmlNamespace(self):
        item = l(content.allBlogItems())[0]
        self.assertEqual("Heiko Behrens", str(item.creator))
        
    def testSingleBlogItem(self):
        id = "http://feedsanitizer.appspot.com/id/http%3A%2F%2Fheikobehrens.net%2F2011%2F03%2F18%2Fwhat-is-eclipsecon%2F"
        item = content.blogItemById(id)
        self.assertEqual(id, str(item.guid))
        self.assertEqual("http://heikobehrens.net/2011/03/18/what-is-eclipsecon/", str(item.link))
        self.assertEqual("Heiko Behrens", str(item.creator))
        
    def testTemplateAccess(self):
        speaker = content.speakerByName("Heiko Behrens")
        
        self.assertEqual("Heiko Behrens", str(speaker['name']))
        self.assertEqual("hbehrens", str(speaker['id']))
        self.assertEqual("/media/files/speakers/hbehrens.png", str(speaker['pictureurl']))
        
        self.assertEqual(1, len(l(speaker['sessions'])))
        session = l(speaker['sessions'])[0]
        self.assertEqual("2075", str(session['id']))

if __name__ == "__main__":
    import sys;sys.argv = ['', 'Test.testList']
    unittest.main()