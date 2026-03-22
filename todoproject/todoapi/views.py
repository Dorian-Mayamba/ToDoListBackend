from rest_framework import viewsets, permissions
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework.parsers import JSONParser
from django.http import HttpResponse, JsonResponse
from .serializers import TaskSerializer
from .models import Task

# Create your views here.
    
class TaskViewSet(viewsets.ModelViewSet):
    serializer_class = TaskSerializer
    permission_classes = [permissions.IsAuthenticated]

    def get_queryset(self):
        return Task.objects.filter(user=self.request.user)

    def perform_create(self, serializer):
        serializer.save(user=self.request.user)
        
        
