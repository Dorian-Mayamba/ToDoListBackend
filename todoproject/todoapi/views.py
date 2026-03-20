from rest_framework import generics, permissions
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework.parsers import JSONParser
from django.http import HttpResponse, JsonResponse
from .serializers import TaskSerializer
from .models import Task

# Create your views here.

        
class ToDoListView(APIView):

    def get(self, request):
        todos = Task.objects.all()
        todo_serializer = TaskSerializer(todos, many=True)
        return JsonResponse(todo_serializer.data, safe=False)
    
    def post(self, request):
        data = JSONParser(request)
        taskSerializer = TaskSerializer(data=data)
        if taskSerializer.is_valid():
            taskSerializer.save()
            return JsonResponse(taskSerializer.data, status=201)
        return JsonResponse(taskSerializer.errors, status=401)
        
        
