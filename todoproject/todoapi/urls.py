from django.urls import path, include
from rest_framework.routers import DefaultRouter
from todoapi.views import TaskViewSet

router = DefaultRouter()
router.register(r'tasks', TaskViewSet, basename='task')

urlpatterns = [
    path("auth/", include('dj_rest_auth.urls')),
    path('auth/register', include('dj_rest_auth.registration.urls')),
    path('', include(router.urls))
]


