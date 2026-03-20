from rest_framework import serializers
from .models import Task, User
from django.contrib.auth.hashers import make_password

class UserSerializer(serializers.Serializer):
    first_name = serializers.CharField(max_length=255)
    last_name = serializers.CharField(max_length=255)
    email = serializers.EmailField()
    password = serializers.CharField(max_length=200)
        
    def save(self, request):
        print(self)
        user = User.objects.create_user(
            username=self.data['email'],
            email=self.data['email'],
            password=self.data['password']
        )
        user.first_name = self.data['first_name']
        user.last_name = self.data['last_name']
        user.save()
        
        return user
    
   
class ChoiceField(serializers.Field):
    def __init__(self, choices, **kwargs):
        self.choices = choices
        super(ChoiceField, self).__init__(**kwargs)
    def to_representation(self, value):
        return self.choices[value]

    def to_internal_value(self, data):
        return getattr(self.choices, data)
        
class TaskSerializer(serializers.Serializer):
    name = serializers.CharField(max_length=200)
    priority = serializers.CharField(max_length=200)
    status = ChoiceField(choices=Task.STATUS)
    
    def save(self, request):
        new_task = Task(
            name=self.data['name'],
            priority=self.data['priority'],
            status=self.data['status']
        )
        new_task.user = request.user
        new_task.save()
        return new_task
    