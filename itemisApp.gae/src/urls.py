from django.conf.urls.defaults import *

handler500 = 'djangotoolbox.errorviews.server_error'

urlpatterns = patterns('',
    (r'^$', 'views.session_all'),
    
    (r'^sessions/$', 'views.session_all'),
    
    (r'^sessions/day/(?P<day>\d+)/$', 'views.session_all'),
    
    (r'^myschedule/$', 'views.session_favorites'),
    
    (r'^myschedule/day/(?P<day>\d+)/$', 'views.session_favorites'),

    (r'^sessions/(?P<id>\d+)/$', 'views.session_details'),
    
    (r'^myschedule/add/(?P<id>\d+)/$', 'views.session_add'),
    
    (r'^myschedule/remove/(?P<id>\d+)/$', 'views.session_remove'),
    
    (r'^speakers/$', 'views.speaker_all'),
    
#    (r'^speakers/name/(?P<name>.+?)/$', 'views.speaker_details_by_name'),
    
    (r'^speakers/name/(?P<name>.+?)/$', 'views.speaker_details_by_name'),
    
    #(r'^speakers/(?P<id>.+?)/$', 'views.speaker_details'),
    
    (r'^update/$', 'logic.update'),
    
    (r'^allSpeakers/$', 'generated_views.allSpeakers'),
    
    (r'^speaker/id/(?P<id>.+?)/$', 'generated_views.speaker_resolver'),
    
    
    (r'^speaker/name/(?P<name>.+?)/$', 'generated_views.speaker_byName'),

    (r'^session/id/(?P<id>.+?)/$', 'generated_views.session_resolver'),
    
    (r'^sessions/day/(?P<day>.+?)/$', 'generated_views.sessions_byDay'),
    #('^$', 'django.views.generic.simple.direct_to_template',
    # {'template': 'home.html'}),
)
